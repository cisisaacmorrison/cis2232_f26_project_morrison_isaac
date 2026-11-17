# cis2232_f26_project_morrison_isaac

# CIS 2232 - AOOP

## Aldo Reny's Gourmet Panini

This repository is for my CIS 2232 Advanced Object-Oriented Programming project.

---

## About the Project

For this project, we are building an ordering system for **Aldo Reny's Gourmet Panini**, a local Italian sandwich shop.

The shop focuses on locally sourced vegetables, homemade breads and sauces, and imported Italian meats and cheeses. Customers can either choose one of the sandwiches already on the menu or build their own.

For a custom sandwich, customers can choose a size, bread, toppings, and optional sauces. The first four ingredient selections are included in the base price. Additional ingredients cost extra depending on their category.

The application keeps track of panini orders and calculates the price of each sandwich, including additional ingredients and tax.

---

## Project Team

| Role | Person |
| -------------------- | -------------- |
| Business Client / BA | Lindsay |
| Developer | Isaac Morrison |
| Project Manager / QA | Misha |

---

## Sandwich Sizes and Bread

There are two sandwich sizes available:

| Size | Price |
| ----- | ------ |
| Lunch | $10.00 |
| Full | $15.00 |

Customers can choose between two types of bread:

| Bread | Extra Cost |
| -------- | ---------- |
| Ciabatta | $0.00 |
| Focaccia | $2.00 |

---

## Menu

The initial preset sandwiches are:

**Classic Italian**
- Salami
- Pepperoni
- Ham
- Provolone

**Caprese**
- Tomato
- Mozzarella
- Basil

**Meatball**
- Meatball
- Tomato
- Mozzarella

**Chicken Pesto**
- Chicken
- Provolone
- Pesto sauce

**Vegetariano**
- Tomato
- Peppers
- Mozzarella
- Pesto sauce

There is also an option to build a custom sandwich instead of choosing a preset.

---

## Ingredients

The ingredients being used for the project are:

**Toppings**
- Mozzarella
- Provolone
- Salami
- Pepperoni
- Ham
- Meatball
- Chicken
- Tomato
- Peppers

**Sauces**
- Pesto
- Mayo
- Olive Oil
- Balsamic

Customers ordering a custom sandwich can select multiple toppings and sauces. At least one topping is required, but sauces are optional.

---

## Data Being Tracked

The application stores information about each panini order, including:

| Field | Purpose |
| ---------------- | -------------------------------- |
| `orderId` | Identifies an order |
| `orderDate` | Stores the date of the order |
| `sandwichName` | Stores the selected sandwich |
| `sandwichSize` | Stores the selected sandwich size |
| `breadType` | Stores the selected bread |
| `customOrder` | Identifies whether the sandwich is custom |
| `ingredients` | Stores the selected toppings |
| `sauce` | Stores the selected sauces |
| `orderTotal` | Stores the calculated order total |

Temporary selections are also used when customers choose toppings and sauces on the order form.

---

## How the Price is Calculated

The price starts with the size of the sandwich.

A Lunch sandwich costs **$10.00**, while a Full sandwich costs **$15.00**.

Other costs can then be added depending on what the customer selects:

- Focaccia: **+$2.00**
- Extra meat or cheese: **+$2.00 each**
- Extra vegetable or sauce: **+$1.50 each**

The first four combined topping and sauce selections on a custom sandwich are included in the base price. Additional selections are charged according to their category.

The application then calculates 15% tax and adds it to the subtotal to get the final price.

---

## Application Functionality

The application currently supports MVC CRUD operations for panini orders using Spring Data JPA.

Users can:

- **Create:** Add a new panini order.
- **Read:** View all saved panini orders in a table.
- **Update:** Edit an existing panini order.
- **Delete:** Remove an existing panini order.

### Form Validation

The order form includes validation to ensure that:

- An order date is selected.
- A sandwich type is selected.
- A sandwich size is selected.
- A bread type is selected.
- At least one topping is selected when creating a custom sandwich.

Sauces are optional.

---

## Project Appearance

The application's colour palette uses muted sage green, dark brown, and complementary accent colours.

| Colour | Hex Code |
| ---------------- | -------- |
| Primary Sage Green | `#90A583` |
| Light Sage Green | `#9AB9AF` |
| Dark Brown | `#593837` |
| Gold | `#9D8420` |
| Deep Red Accent | `#942911` |
| White | `#FFFFFF` |

---

## Technology

The application uses:

- Java 21
- Spring Boot
- Maven
- Thymeleaf
- Spring Data JPA
- JDBC
- MariaDB / MySQL
- HTML, CSS, and JavaScript

---

## Panini Sales Report

The report shows sandwich sales for a selected date range.

Users enter a start date and end date to generate the report.

The report shows:

- Total orders
- Total sandwiches sold
- Total sales
- Quantity sold for each sandwich
- A bar chart of sandwich sales
- A list of orders

The report uses JDBC to retrieve information from the database and saves a text file in `C:\cis2232\`.

### Testing

Using the sample data from October 26–29, 2026, the expected results are:

- 8 orders
- 8 sandwiches
- $109.92 in sales

---

## Project Status

**Current Sprint:** Sprint 3 of 5

**Project:** Aldo Reny's Gourmet Panini

**Developer:** Isaac Morrison
