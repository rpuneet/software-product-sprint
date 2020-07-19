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
const ABOUT_ID = "about";

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
const ROW_LOGO_LINK = "row-logo-link";

// ID
const QUOTE_ID = "quote"
const COMMENT_FORM_ID = 'post-comment';
const COMMENT_ID = 'comments'
// Utilities

/**
 * Checks if the given string is empty of whitespace.
 * 
 * @param {string} data 
 * @returns {Boolean} if data is not string or empty returns true.
 */
const isEmptyOrWhiteSpace = (data) => {
  if (typeof data === 'string') {
    return data === null || 
            data === undefined || 
            data.trim() === '';
  }
  return true;
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

const createRowLogoWithLink = (logoPath, link) => {
  const rowLogo = createRowLogo(logoPath);

  if (!isEmptyOrWhiteSpace(link)) {
    const outerDiv = document.createElement("div");
    outerDiv.className = ROW_LOGO_LINK;

    const rowLogoWithLink = document.createElement("a");
    rowLogoWithLink.href = link;

    rowLogoWithLink.appendChild(rowLogo);
    outerDiv.appendChild(rowLogoWithLink)
    return outerDiv;
  }
  return rowLogo;
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
      createRowLogoWithLink(details.logoPath, details.link)
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
  document.getElementById(ABOUT_ID).style.display = "none";

  document.getElementById("navbar-experience").className = "navbar-item";
  document.getElementById("navbar-education").className = "navbar-item";
  document.getElementById("navbar-projects").className = "navbar-item";
  document.getElementById("navbar-about").className = "navbar-item";
}

const showExperienceSection = () => {
  removeAllSections();
  const experienceSectionDiv = createMenuSection(experienceDetails);
  const experienceSection = document.getElementById(EXPERIENCE_ID);
  experienceSection.appendChild(experienceSectionDiv);
  document.getElementById("navbar-experience").className = "navbar-item item-selected";
}

const showEducationSection = () => {
  removeAllSections();
  const educationSectionDiv = createMenuSection(educationDetails);
  document.getElementById(EDUCATION_ID).appendChild(educationSectionDiv);
  document.getElementById("navbar-education").className = "navbar-item item-selected";

}

const showProjectsSection = () => {
  removeAllSections();
  const projectsSectionDiv = createMenuSection(projectDetails);
  document.getElementById(PROJECTS_ID).appendChild(projectsSectionDiv);
  document.getElementById("navbar-projects").className = "navbar-item item-selected";
}

const showAboutSection = () => {
  removeAllSections();
  document.getElementById(ABOUT_ID).style.display = "block";
  document.getElementById("navbar-about").className = "navbar-item item-selected";
}

const showRandomQuote = () => {
  fetch('/random-quote')
      .then(response => response.json())
      .then(addQuoteToElement)
      .catch(console.log);
}

const addQuoteToElement = quote => {
  const quoteElement = document.getElementById(QUOTE_ID);
  quoteElement.innerText = [quote.quoteText, quote.author].join(' - ');
}

const handleSubmitComment = event => {
  event.preventDefault();

  fetch(event.target.action, {
    method: 'POST',
    body: new URLSearchParams(new FormData(event.target))
  }).then(res => {
    return res.json()
  }).then(body => {
    if (body.valid) {
      showComments()
    } else {
      console.log(body);
    }
  }).catch(console.log)
}

const createComment = comment => {
  const commentElement = document.createElement("div")
  const commentText = document.createElement("p")
  commentText.innerText = comment.commentText;
  commentElement.appendChild(commentText);
  return commentElement
}

const showComments = () => {
  const commentElement = document.getElementById(COMMENT_ID);

  fetch("/comments")
      .then(res => res.json())
      .then(comments => {
        commentElement.innerHTML = ""
        comments.map(comment => {
          commentElement.appendChild(createComment(comment))
        })
      })
}


window.onload = () => {
  document.getElementById("navbar-experience").addEventListener("click", showExperienceSection);
  document.getElementById("navbar-education").addEventListener("click", showEducationSection);
  document.getElementById("navbar-projects").addEventListener("click", showProjectsSection);
  document.getElementById("navbar-about").addEventListener("click", showAboutSection);

  document.forms[COMMENT_FORM_ID].addEventListener('submit', handleSubmitComment)

  showAboutSection();
  showRandomQuote();
  showComments()
}