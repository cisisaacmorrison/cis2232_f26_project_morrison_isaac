# For localhost
DROP DATABASE IF EXISTS cis2232_aldo_renys_panini;
CREATE DATABASE cis2232_aldo_renys_panini;
USE cis2232_aldo_renys_panini;

-- Customer orders

CREATE TABLE panini_order
(
    order_id    INT           NOT NULL AUTO_INCREMENT,
    order_date  DATE          NOT NULL,
    subtotal    DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    tax         DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    order_total DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (order_id)
);

-- Individual sandwiches belonging to an order

CREATE TABLE panini_sandwich
(
    sandwich_id    INT           NOT NULL AUTO_INCREMENT,
    order_id       INT           NOT NULL,
    sandwich_size  VARCHAR(20)   NOT NULL,
    bread_type     VARCHAR(50)   NOT NULL,
    is_custom      BOOLEAN       NOT NULL,
    preset_name    VARCHAR(100),
    sandwich_total DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (sandwich_id),
    FOREIGN KEY (order_id) REFERENCES panini_order (order_id)
);

-- Available ingredients and their extra costs

CREATE TABLE panini_ingredient
(
    ingredient_id         INT           NOT NULL AUTO_INCREMENT,
    ingredient_name       VARCHAR(50)   NOT NULL,
    ingredient_category   VARCHAR(20)   NOT NULL,
    ingredient_extra_cost DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (ingredient_id)
);

-- Ingredients selected for each sandwich

CREATE TABLE panini_sandwich_ingredient
(
    sandwich_id    INT NOT NULL,
    ingredient_id  INT NOT NULL,
    quantity       INT NOT NULL DEFAULT 1,
    extra_quantity INT NOT NULL DEFAULT 0,
    PRIMARY KEY (sandwich_id, ingredient_id),
    FOREIGN KEY (sandwich_id) REFERENCES panini_sandwich (sandwich_id),
    FOREIGN KEY (ingredient_id) REFERENCES panini_ingredient (ingredient_id)
);

-- Ingredient records

INSERT INTO panini_ingredient
    (ingredient_name, ingredient_category, ingredient_extra_cost)
VALUES ('Mozzarella', 'Topping', 2.00),
       ('Provolone', 'Topping', 2.00),
       ('Salami', 'Topping', 2.00),
       ('Pepperoni', 'Topping', 2.00),
       ('Ham', 'Topping', 2.00),
       ('Meatball', 'Topping', 2.00),
       ('Chicken', 'Topping', 2.00),
       ('Tomato', 'Topping', 1.50),
       ('Peppers', 'Topping', 1.50),
       ('Basil', 'Topping', 1.50),
       ('Pesto', 'Sauce', 1.50),
       ('Mayo', 'Sauce', 1.50),
       ('Olive Oil', 'Sauce', 1.50),
       ('Balsamic', 'Sauce', 1.50);

-- Test data for Panini Sales Report

INSERT INTO panini_order
(order_date, sandwich_size, bread_type, custom_order,
 sandwich_name, ingredients, sauce, order_total)
VALUES
    ('2026-10-29', 'Medium', 'Italian', 0,
     'Classic Italian', 'Ham, salami, provolone', 'Mayo', 12.99),

    ('2026-10-29', 'Large', 'Whole Wheat', 0,
     'Classic Italian', 'Ham, salami, provolone', 'Mayo', 15.99),

    ('2026-10-29', 'Small', 'White', 0,
     'Chicken Panini', 'Chicken, mozzarella, lettuce', 'Ranch', 10.99),

    ('2026-10-28', 'Medium', 'Italian', 0,
     'Chicken Panini', 'Chicken, mozzarella, lettuce', 'Ranch', 12.99),

    ('2026-10-28', 'Large', 'Whole Wheat', 0,
     'Veggie Panini', 'Peppers, onions, spinach, cheese', 'Pesto', 14.99),

    ('2026-10-27', 'Medium', 'Italian', 1,
     'Custom Panini', 'Turkey, bacon, cheddar', 'Chipotle', 13.99),

    ('2026-10-27', 'Small', 'White', 0,
     'Veggie Panini', 'Peppers, onions, spinach, cheese', 'Pesto', 11.99),

    ('2026-10-26', 'Large', 'Italian', 0,
     'Classic Italian', 'Ham, salami, provolone', 'Mayo', 15.99);

CREATE TABLE SkillsAssessmentSquashTechnical
(
    id                int(5),
    assessmentDate    varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    athleteName       varchar(50) NOT NULL COMMENT 'Athletes name',
    assessorName      varchar(50) NOT NULL COMMENT 'Athletes name',
    forehandDrives    int(5) COMMENT 'Number of forehand drives',
    backhandDrives    int(5) COMMENT 'Number of backhand drives',
    forehandVolleyMax int(5) COMMENT 'Max number of forehand volleys',
    forehandVolleySum int(5) COMMENT 'Sum of forehand volleys',
    backhandVolleyMax int(5) COMMENT 'Max number of backhand volleys',
    backhandVolleySum int(5) COMMENT 'Sum of backhand volleys',
    technicalScore    int(5) COMMENT 'Score calculated at submission'
) COMMENT 'This table holds technical skills assessment details';

ALTER TABLE SkillsAssessmentSquashTechnical
    ADD PRIMARY KEY (id);
ALTER TABLE SkillsAssessmentSquashTechnical
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;