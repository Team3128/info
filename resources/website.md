# Website Information
## General Info
Team 3128's website is hosted on GitHub. The entire website has been made using something called [Jekyll]("https://jekyllrb.com/"), which is a program that converts text content into a static webpage. All of the formatting is done through [HTML]("http://www.w3schools.com/html/default.asp") and [CSS]("http://www.w3schools.com/css/default.asp"), and all of the webpages are written in [Markdown]("https://daringfireball.net/projects/markdown/"). Because the site is hosted on GitHub, everyone on our team has the ability to edit the website.

## Editing Prerequisites
*To change information/add pages:* The ability to edit text
*To change formatting/styles:* Basic knowledge of HTML and CSS

## Website Structure
This will be a quick overview of how to edit/create pages.(All the information pertaining to Jekyll is on [their website]("https://jekyllrb.com/")) The base of the site is written in HTML. For instance, the homepage layout is written like so:

<pre><code>
<!DOCTYPE html>
<html>
  {% include head.html %}
  <body>
    {% include header.html %}
    
    <img src="/resources/home_image.jpeg" height="50px" width="100%"></img>
    <div class="page-content">
      <div class="wrapper">
        {{ content }}
      </div>
    </div>
    
    {% include footer.html %}
  </body>
</html>
</pre></code>

Let's talk about everything you need to know.
 - {% include "file name here" %} - This the Jekyll's way of including HTML files in a singular line. Let's say you have code for a sidebar and you wanted to put it in the page layout. Rather than copy and pasting that code into every single page you want it in, you could just pop it in the "_include" directory and add {% include sidebar.html %} everywhere you want it
 - {{ content }} - When you create a new page with an existing layout, Jekyll will put that in place of the {{ content }} of the new HTML
 
 That's literally it.
 
## Creating Pages
Every single page on the site is created in Markdown(except for the homepage). To create a new page, you need to create a new .md file and name it the name of the page. Once created, the MOST important thing to do is to specify the **front matter**. All that is is a few lines of information at the top of every page that tells the website where a page lives, what layout to use, and anything else. This is the format of front matter:

---
layout: "layout name"
title: "page title"
shownav: "true/false"
permalink: "permalink"
---

*layout:* Our website has three layouts: "page"(a generic page), "stusub"(a page that belongs in the students directory), and "parpage"(a page that belongs in the parents directory)
*title:* Here, you put what you want the title of the page to be
*shownav:* If you want the page to show up in the navigation bar, you make this true.
UPDATE: shownav has been depreciated. All navigation bar changes are manual within the _include file "header.html"
*permalink:* This page will show as http://team3128.org/"permalink". IMPORTANT: make sure the permalink begins and ends with a /. When nesting a page under Students of Parents, make the permalink show as "/students/'name'/" or "/parents/'name'/"

Fromt here, you just  type into the page just as you would like it to show online

## Editing Pages
It's simple. Open up a page markdown, change the content, and you're done!

*A note about modifying the website: All edits are NOT ANONYMOUS and are linked to your GitHub account. We can easily rollback changes and making inappropriate or disrespectful changes will be met with consequences. Use common sense and be responsible*

## Changing the Structure of the Site
TBA

## Changing the Appearance of the Site
TBA
