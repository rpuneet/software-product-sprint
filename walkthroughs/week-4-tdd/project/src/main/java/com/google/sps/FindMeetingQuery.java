// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.*;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    HashSet<String> attendees = new HashSet<>(request.getAttendees());
    ArrayList<TimeRange> timeRangesNotAvailable = getTimeRangesNotAvailable(events, attendees);
    ArrayList<TimeRange> availableTimeSlots = new ArrayList<>();

    if (timeRangesNotAvailable.isEmpty()) {
      checkTimeRangeAndAddToAvailable(TimeRange.START_OF_DAY,
              TimeRange.END_OF_DAY + 1,
              request.getDuration(),
              availableTimeSlots);
      return availableTimeSlots;
    }

    timeRangesNotAvailable.sort(TimeRange.ORDER_BY_START);

    TreeSet<Integer> endTimeOfEvents = new TreeSet<>();
    int currentStart = TimeRange.START_OF_DAY;

    for (TimeRange timeRange: timeRangesNotAvailable) {
      while (!endTimeOfEvents.isEmpty() && endTimeOfEvents.first() < timeRange.start()) {
        currentStart = endTimeOfEvents.first();
        endTimeOfEvents.remove(endTimeOfEvents.first());
      }
      if (endTimeOfEvents.isEmpty()) {
        checkTimeRangeAndAddToAvailable(currentStart,
                timeRange.start(),
                request.getDuration(),
                availableTimeSlots);
      }
      endTimeOfEvents.add(timeRange.end());
    }

    while (!endTimeOfEvents.isEmpty()) {
      currentStart = endTimeOfEvents.first();
      endTimeOfEvents.remove(endTimeOfEvents.first());
    }
    checkTimeRangeAndAddToAvailable(
            currentStart,
            TimeRange.END_OF_DAY + 1,
            request.getDuration(),
            availableTimeSlots
    );
    return availableTimeSlots;
  }

  private ArrayList<TimeRange> getTimeRangesNotAvailable(Collection<Event> events, Collection<String> attendees) {
    ArrayList<TimeRange> timeRangesNotAvailable = new ArrayList<>();

    for (Event event : events) {
      for (String attendee: event.getAttendees()) {
        if (attendees.contains(attendee)) {
          timeRangesNotAvailable.add(event.getWhen());
        }
      }
    }
    return timeRangesNotAvailable;
  }

  private void checkTimeRangeAndAddToAvailable(int start, int end, long durationRequested, Collection<TimeRange> availableTimeSlots) {
    if ((end - start) >= durationRequested) {
      availableTimeSlots.add(
              TimeRange.fromStartEnd(start, end, false)
      );
    }
  }
}
