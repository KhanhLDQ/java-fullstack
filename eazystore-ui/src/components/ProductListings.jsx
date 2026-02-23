import { React, useState, useMemo } from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import DropDown from "./DropDown";

const sortList = ["Popularity", "Price Low to High", "Price High to Low"];

//React is not tracking the variable and will never know whenever this variable is updated -> cannot trigger re-renders in React
export default function ProductListings({ products }) {
  //hooks can only be used in React functional components
  //hooks must be called at the top level of a component
  //hooks cannot be used inside loops|conditions|nested functions
  //https://react.dev/reference/react/useState
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState("Popularity");

  //useMemo caches a calculation result between re-renders until its dependencies change
  //https://react.dev/reference/react/useMemo
  const filteredAndSortedProducts = useMemo(() => {
    if (!Array.isArray(products)) {
      return [];
    }

    let filteredProducts = products.filter(
      (product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase()) ||
        product.description.toLowerCase().includes(searchText.toLowerCase()),
    );

    return filteredProducts.slice().sort((a, b) => {
      switch (selectedSort) {
        case "Price Low to High":
          return parseFloat(a.price) - parseFloat(b.price);
        case "Price High to Low":
          return parseFloat(b.price) - parseFloat(a.price);
        case "Popularity":
        default:
          return parseFloat(b.popularity) - parseFloat(a.popularity);
      }
    });
  }, [products, searchText, selectedSort]);

  //event handler function
  function handleSearchChange(inputSearch) {
    setSearchText(inputSearch); //trigger ProductListings re-render
  }

  function handleSortChange(sortType) {
    setSelectedSort(sortType);
  }

  return (
    <div className="max-w-6xl mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Search"
          placeholder="Search products..."
          value={searchText}
          handleSearch={(value) => handleSearchChange(value)}
        />
        <DropDown
          label="Sort by"
          options={sortList}
          selectedValue={selectedSort}
          handleSort={(value) => handleSortChange(value)}
        />
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {filteredAndSortedProducts.length > 0 ? (
          filteredAndSortedProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary dark:text-light">
            No Products Found!
          </p>
        )}
      </div>
    </div>
  );
}
