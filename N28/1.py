class ShoppingCart:
    def __init__(self, items):
        self.items = items  # shares the reference passed in!

    def add_item(self, item):
        self.items.append(item)

    def get_total(self):
        return sum(i["price"] for i in self.items)


# --- Usage ---
default_items = [{"name": "Welcome Gift", "price": 0}]

cart_a = ShoppingCart(default_items)
cart_b = ShoppingCart(default_items)

cart_a.add_item({"name": "Laptop", "price": 1200})

print(cart_b.get_total())  # 1200 😱 — cart_b now has a laptop in it!
