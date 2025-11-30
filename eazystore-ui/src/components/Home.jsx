import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useState, useEffect } from "react";

export default function Home() {
  // state variable name | function to update data into state variable (setVariable_Name)
  const [products, setProducts] = useState([]); //initial state

  //mount -> react creates the component instance - converts JSX to actual HTML elements - add elements to the DOM
  //run once when the component mounts (show up for the first time)
  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    //axios GET request (async default -> response come back later)
    //await -> wait BE response before executing the next logic
    const response = await apiClient.get("/products");
    //update products state with fetched data
    setProducts(response.data);
  };

  return (
    <div className="max-w-6xl mx-auto px-6 py-8">
      <PageHeading title="Explore Eazy Stickers!">
        Add a touch of creativity to your space with our wide range of fun and
        unique stickers. Perfect for any occasion!
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}
