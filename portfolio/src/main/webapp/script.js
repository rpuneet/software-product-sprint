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

// Section ID
const EXPERIENCE_ID = "experience";
const EDUCATION_ID = "education";
const PROJECTS_ID = "projects";
const CONTACT_ID = "contact";

// Class Names
const MENU_ITEM = "menu-item";
const CONTAINER = "container"
const CARD = "card";
const SECTION_ROW = "section-row";
const SECTION_ROW_LOGO = "section-row-logo";
const LOGO = "logo";
const ROW_INFO = "row-info";
const ROW_TITLE = "row-title";
const ROW_META = "row-meta";
const ROW_ADDITIONAL = "row-additional";
const ROW_LIST_ITEMS = "row-list-items";

// Utilities

/**
 * Checks if the given string is empty of whitespace.
 * 
 * @param {string} data 
 * @returns {Boolean} if data is not string or empty returns true.
 */
const isEmptyOrWhiteSpace = (data) => {
  if (typeof value === 'string') {
    data === null || !data.trim();
  }
  return false;
}

// Creating Menu Items
const createRowLogo = logoPath => {
  const sectionRowLogo = document.createElement("div");
  sectionRowLogo.className = SECTION_ROW_LOGO;

  const img = document.createElement("img");
  img.src = logoPath;
  img.className = LOGO;

  sectionRowLogo.appendChild(img);
  return sectionRowLogo
}

const createRowTitle = title => {
  const titleElement = document.createElement("h3");
  titleElement.className = ROW_TITLE;
  titleElement.textContent = title;
  return titleElement;
}

const createRowMeta = meta => {
  const titleElement = document.createElement("p");
  titleElement.className = ROW_META;
  titleElement.textContent = meta;
  return titleElement;
}

const createRowAddtional = content => {
  const titleElement = document.createElement("p");
  titleElement.className = ROW_ADDITIONAL;
  titleElement.textContent = content;
  return titleElement;
}

const createRowListItems = items => {
  const rowListItems = document.createElement("ul");
  rowListItems.className = ROW_LIST_ITEMS;

  items.forEach(item => {
    const item_li = document.createElement("li");
    item_li.textContent = item;
    rowListItems.appendChild(item_li);
  })
  return rowListItems;
}


const createSectionRow = details => {
  const sectionRow = document.createElement("div");
  sectionRow.className = SECTION_ROW;

  if (!isEmptyOrWhiteSpace(details.logoPath)) {
    sectionRow.appendChild(
      createRowLogo(details.logoPath)
    );
  }
  
  const rowInfo = document.createElement("div");
  rowInfo.className = ROW_INFO;

  if (!isEmptyOrWhiteSpace(details.title)) {
    rowInfo.appendChild(
      createRowTitle(details.title)
    );
  }

  if (!isEmptyOrWhiteSpace(details.subtitle)) {
    rowInfo.appendChild(
      createRowMeta(details.subtitle)
    );
  }

  if (!isEmptyOrWhiteSpace(details.duration)) {
    rowInfo.appendChild(
      createRowMeta(details.duration)
    );
  }

  if (!isEmptyOrWhiteSpace(details.additional)) {
    rowInfo.appendChild(
      createRowAddtional(details.additional)
    );
  }

  if (details.listItems) {
    rowInfo.appendChild(
      createRowListItems(details.listItems)
    );
  }

  sectionRow.appendChild(rowInfo);
  return sectionRow;
}

const createMenuSection = menuDetails => {
  const experienceDiv = document.createElement("div");
  experienceDiv.className = CARD;

  menuDetails.forEach((details, index) => {
    experienceDiv.appendChild(createSectionRow(details));
    if (index < menuDetails.length - 1) {
      experienceDiv.appendChild(document.createElement("hr"));
    }
  });

  return experienceDiv
}

const removeAllSections = () => {
  document.getElementById(EXPERIENCE_ID).innerHTML = "";
  document.getElementById(EDUCATION_ID).innerHTML = "";
  document.getElementById(PROJECTS_ID).innerHTML = "";
  document.getElementById(CONTACT_ID).innerHTML = "";
}

const showExperienceSection = () => {
  removeAllSections();
  const experienceSectionDiv = createMenuSection(experienceDetails);
  document.getElementById(EXPERIENCE_ID).appendChild(experienceSectionDiv);
}

const showEducationSection = () => {
  removeAllSections();
  const educationSectionDiv = createMenuSection(educationDetails);
  document.getElementById(EDUCATION_ID).appendChild(educationSectionDiv)
}

window.onload = () => {
  document.getElementById("navbar-experience").addEventListener("click", showExperienceSection);
  document.getElementById("navbar-education").addEventListener("click", showEducationSection);
}