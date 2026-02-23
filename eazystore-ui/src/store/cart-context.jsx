import {
  createContext,
  useState,
  useEffect,
  useContext,
  useReducer,
} from "react";

// const initialCartContext = {
//   cart: [],
//   setCart: () => {},
//   addToCart: () => {
//     console.log("Product added to cart!");
//   },
//   removeFromCart: () => {},
//   totalQuantity: 0,
// };

//step I: create a context that will hold the cart data
export const CartContext = createContext();

const ADD_TO_CART = "ADD_TO_CART";
const REMOVE_FROM_CART = "REMOVE_FROM_CART";
const CLEAR_CART = "CLEAR_CART";

const cartReducer = (prevCart, action) => {
  switch (
    action.type //return new state value
  ) {
    case ADD_TO_CART:
      const { product, quantity } = action.payload;
      const existingItem = prevCart.find(
        (item) => item.productId === product.productId,
      );

      if (existingItem) {
        return prevCart.map((item) =>
          item.productId === product.productId
            ? { ...item, quantity: item.quantity + quantity }
            : item,
        );
      }

      return [...prevCart, { ...product, quantity }];
    case REMOVE_FROM_CART:
      return prevCart.filter(
        (item) => item.productId !== action.payload.productId,
      );
    case CLEAR_CART:
      return [];
    default:
      return prevCart;
  }
};

//build custom hook
export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  const initialCartState = (() => {
    try {
      const storedCart = localStorage.getItem("cart");
      return storedCart ? JSON.parse(storedCart) : [];
    } catch (error) {
      console.error("Failed to parse cart from localStorage: ", error);
      return [];
    }
  })(); //immediately execute function -> assign result to initialCartState

  const [cart, dispatch] = useReducer(cartReducer, initialCartState);

  //init cart state from localStorage or as an empty array
  //init function -> runs once - reads localStorage -> after that - manage React state in memory
  //   const [cart, setCart] = useState(() => {
  //     try {
  //       const storedCart = localStorage.getItem("cart");
  //       return storedCart ? JSON.parse(storedCart) : [];
  //     } catch (error) {
  //       console.error("Failed to parse cart from localStorage: ", error);
  //       return [];
  //     }
  //   });

  //save cart to localStorage whenever it changes
  //without this useEffect -> lose all cart data when the user refreshes or returns later!
  useEffect(() => {
    try {
      localStorage.setItem("cart", JSON.stringify(cart));
    } catch (error) {
      console.error("Failed to save cart to localStorage: ", error);
    }
  }, [cart]); //run whenever cart is updated

  //   const addToCart = (product, quantity) => {
  //     //React auto provides previous state as the parameter
  //     setCart((prevCart) => {
  //       const existingItem = prevCart.find(
  //         (item) => item.productId === product.productId
  //       );

  //       if (existingItem) {
  //         //use map() to create a new array with updated quantity
  //         return prevCart.map((item) =>
  //           item.productId === product.productId
  //             ? { ...item, quantity: item.quantity + quantity } //spread operator -> create a new object with item's properties, but increase the quantity
  //             : item
  //         );
  //       }

  //       //create a new array with all existing cart items -> add new product into it
  //       return [...prevCart, { ...product, quantity }];
  //     });
  //   };

  //   const removeFromCart = (productId) => {
  //     setCart(
  //       //return a new array with only items matching condition
  //       (prevCart) => prevCart.filter((item) => item.productId !== productId)
  //     );
  //   };

  const addToCart = (product, quantity) => {
    dispatch({ type: ADD_TO_CART, payload: { product, quantity } });
  };

  const removeFromCart = (productId) => {
    dispatch({ type: REMOVE_FROM_CART, payload: { productId } });
  };

  const clearCart = () => {
    dispatch({ type: CLEAR_CART });
  };

  //calculate total quantity
  const totalQuantity = cart.reduce((acc, item) => acc + item.quantity, 0);

  return (
    <CartContext.Provider
      value={{ cart, addToCart, removeFromCart, clearCart, totalQuantity }}
    >
      {/* without children -> cart context provider will never know about your child components */}
      {children}
    </CartContext.Provider>
  );
};
