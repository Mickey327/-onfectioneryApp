CREATE TABLE category(
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT NOT NULL
);
CREATE TABLE product(
  id SERIAL NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  weight DECIMAL NOT NULL,
  price DECIMAL NOT NULL,
  category_id INTEGER NOT NULL,
  image_name TEXT,
  description TEXT,
  CONSTRAINT FK_CATEGORY_ID FOREIGN KEY(category_id) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE users(
    id SERIAL NOT NULL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);
CREATE TABLE role(
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);
CREATE TABLE users_to_role(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT FK_USER_ID FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_ROLE_ID FOREIGN KEY(role_id) REFERENCES role(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE cart(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer NOT NULL
);
CREATE TABLE cartitem(
    cart_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT FK_CART_ID FOREIGN KEY(cart_id) references cart(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_PRODUCT_ID FOREIGN KEY(product_id) references product(id) ON DELETE CASCADE  ON UPDATE CASCADE
);
CREATE TABLE orders(
  id SERIAL NOT NULL,
  cart_id integer NOT NULL,
  name TEXT NOT NULL,
  surname TEXT NOT NULL,
  city TEXT NOT NULL,
  address TEXT NOT NULL,
  additional_address TEXT,
  email TEXT NOT NULL,
  phone TEXT NOT NULL,
  additional_info TEXT,
  CONSTRAINT FK_CART_ORDER_ID FOREIGN KEY (cart_id) references cart(id) ON DELETE CASCADE ON UPDATE CASCADE
);