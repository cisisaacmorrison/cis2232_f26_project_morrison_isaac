
DROP DATABASE IF EXISTS cis2232_aldo_renys_panini;
CREATE DATABASE cis2232_aldo_renys_panini;
USE cis2232_aldo_renys_panini;

CREATE TABLE panini_order
(
    order_id      INT           NOT NULL AUTO_INCREMENT,
    order_date    DATE          NOT NULL,
    sandwich_size VARCHAR(20)   NOT NULL,
    bread_type    VARCHAR(50)   NOT NULL,
    custom_order  BOOLEAN       NOT NULL,
    sandwich_name VARCHAR(100),
    ingredients   VARCHAR(255),
    sauce         VARCHAR(100),
    order_total   DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (order_id)
);

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

CREATE TABLE panini_ingredient
(
    ingredient_id         INT           NOT NULL AUTO_INCREMENT,
    ingredient_name       VARCHAR(50)   NOT NULL,
    ingredient_category   VARCHAR(20)   NOT NULL,
    ingredient_extra_cost DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (ingredient_id)
);

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

INSERT INTO panini_ingredient
(ingredient_name, ingredient_category, ingredient_extra_cost)
VALUES
    ('Mozzarella', 'Topping', 2.00),
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
    id                INT(5),
    assessmentDate    VARCHAR(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   VARCHAR(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    athleteName       VARCHAR(50) NOT NULL COMMENT 'Athletes name',
    assessorName      VARCHAR(50) NOT NULL COMMENT 'Athletes name',
    forehandDrives    INT(5) COMMENT 'Number of forehand drives',
    backhandDrives    INT(5) COMMENT 'Number of backhand drives',
    forehandVolleyMax INT(5) COMMENT 'Max number of forehand volleys',
    forehandVolleySum INT(5) COMMENT 'Sum of forehand volleys',
    backhandVolleyMax INT(5) COMMENT 'Max number of backhand volleys',
    backhandVolleySum INT(5) COMMENT 'Sum of backhand volleys',
    technicalScore    INT(5) COMMENT 'Score calculated at submission'
) COMMENT 'This table holds technical skills assessment details';

ALTER TABLE SkillsAssessmentSquashTechnical
    ADD PRIMARY KEY (id);

ALTER TABLE SkillsAssessmentSquashTechnical
    MODIFY id INT(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;
