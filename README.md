# cis2232_f26_project_morrison_isaac

# CIS 2232 - AOOP
## Aldo Reny's Gourmet Panini

This repository is for my CIS 2232 Advanced Object-Oriented Programming project.

---

## About the Project

For this project, we are building an ordering system for **Aldo Reny's Gourmet Panini**, a local Italian sandwich shop.

The shop focuses on locally sourced vegetables, homemade breads and sauces, and imported Italian meats and cheeses. Customers can either choose one of the sandwiches already on the menu or build their own.

For a custom sandwich, the customer can choose the size, bread, up to four toppings, and one sauce. Extra ingredients can also be added for an additional cost.

The application will keep track of the orders and calculate the price of each sandwich and the total for the customer's order.

---

## Project Team

| Role | Person |
|---|---|
| Business Client / BA | Lindsay |
| Developer | Isaac Morrison |
| Project Manager / QA | Misha |

---

## Sandwich Sizes and Bread

There are two sandwich sizes available:

| Size | Price |
|---|---:|
| Lunch | $10.00 |
| Full | $15.00 |

Customers can choose between two types of bread:

| Bread | Extra Cost |
|---|---:|
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
- Pesto
- Provolone

**Vegetariano**
- Tomato
- Peppers
- Pesto
- Mozzarella

There will also be an option to build a custom sandwich instead of choosing a preset.

---

## Ingredients

The ingredients being used for the project are:

- Mozzarella
- Provolone
- Salami
- Pepperoni
- Ham
- Meatball
- Chicken
- Tomato
- Peppers
- Pesto
- Mayo
- Olive Oil
- Balsamic

Ingredients will be separated into toppings and sauces. Some ingredients can also be marked as premium.

---

## Data Being Tracked

The application will need to keep track of information such as:

| Field | Type | Purpose |
|---|---|---|
| `sandwichSize` | Enum | Stores the selected sandwich size |
| `breadType` | Enum | Stores the selected bread |
| `ingredientName` | String | Stores the ingredient name |
| `ingredientCategory` | Enum | Identifies a topping or sauce |
| `ingredientIsPremium` | boolean | Identifies premium ingredients |
| `ingredientExtraCost` | double | Stores the extra ingredient cost |
| `isCustom` | boolean | Identifies a custom or preset sandwich |
| `presetName` | String | Stores the name of a preset sandwich |
| `custToppings` | List<Ingredient> | Stores toppings selected for a custom sandwich |
| `custSauce` | Ingredient | Stores the selected sauce |
| `orderID` | int | Identifies an order |
| `orderList` | HashMap | Keeps track of orders |

---

## How the Price is Calculated

The price starts with the size of the sandwich.

A Lunch sandwich costs **$10.00**, while a Full sandwich costs **$15.00**.

Other costs can then be added depending on what the customer selects:

- Focaccia: **+$2.00**
- Extra meat or cheese: **+$2.00**
- Extra vegetable or sauce: **+$1.50**

If a customer orders more than one sandwich, the prices are added together.

The application will then calculate the tax and add it to the subtotal to get the final order total.

The order information can also be used later for looking at sales totals over different periods of time.

---

## Project Appearance

The main project colour will be:

**Muted Teal**

`#94B9AF`

The colour will be used as the main colour for the application's interface.

---

## Technology

The starter application uses:

- Java
- Spring Boot
- Maven
- Thymeleaf
- Spring Data JPA
- MySQL
- GraphQL

---

## Project Status

**Current Sprint:** Sprint 1 of 5

**Project:** Aldo Reny's Gourmet Panini

**Developer:** Isaac Morrison
