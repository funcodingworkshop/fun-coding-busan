from dataclasses import dataclass, field, replace

@dataclass(frozen=True)
class Cart:
    items: tuple

def create_cart(items):
    return Cart(items=tuple(items))

def add_item(cart, item):
    return replace(cart, items=cart.items + (item,))  # new tuple, not mutated

def get_total(cart):
    return sum(i["price"] for i in cart.items)


default_items = [{"name": "Welcome Gift", "price": 0}]

cart_a = create_cart(default_items)
cart_b = create_cart(default_items)

updated_cart_a = add_item(cart_a, {"name": "Laptop", "price": 1200})

# cart_a.items = [] # dataclasses.FrozenInstanceError: cannot assign to field 'items'

# updated_cart_a.items[0]["price"] = 10

print(get_total(cart_b))          # 0 ✅ — cart_b untouched
print(get_total(updated_cart_a))  # 1200 ✅
