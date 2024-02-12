# FOODURA

**FOODURA** - A Java Spring Boot project for order management

## Table of Contents

- [Business Process](#business-process)
- [Database Model](#database-model)
- [Database Queries](#database-queries)

## Business Process

The primary objective of this project is to develop a service for ordering products. The system will allow clients to place orders with products and track the order's progress through various stages. These stages may include "In Process," "On the Way," "Delivered," etc

### Features

- Create and manage orders.
- Add products to orders.
- Track the status of orders.
- ...

## Database Model

### Entities

1. **Person**: Represents individuals who can place orders.
2. **Product**: Represents the products available for order.
3. **Order**: Represents individual orders placed by people.

#### Relationships

- Each **Person** can have multiple **Order** records.
- Each **Order** can have multiple **Product** records.

## Database Queries

### Difficult Query: Show all people who have placed orders containing a specific product

```sql
SELECT DISTINCT p.*
FROM Person p
JOIN Order o ON p.id = o.person_id
JOIN OrderProduct op ON o.id = op.order_id
JOIN Product prod ON op.product_id = prod.id
WHERE prod.name = 'Special Product Name';

