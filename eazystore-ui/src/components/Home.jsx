import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import { useLoaderData } from "react-router-dom";
import { useLocation } from "react-router-dom";

export default function Home() {
  // state variable name | function to update data into state variable (setVariable_Name)
  // const [products, setProducts] = useState([]); //initial state
  // const [loading, setLoading] = useState(true);
  // const [error, setError] = useState(null);

  //mount -> react creates the component instance - converts JSX to actual HTML elements - add elements to the DOM
  //run once when the component mounts (show up for the first time)
  // useEffect(() => {
  //   fetchProducts();
  // }, []);

  // const fetchProducts = async () => {
  //   try {
  //     setLoading(true);
  //     //axios GET request (async default -> response come back later)
  //     //await -> wait BE response before executing the next logic
  //     const response = await apiClient.get("/products");
  //     //update products state with fetched data
  //     setProducts(response.data);
  //   } catch (error) {
  //     setError(
  //       //optional chaining operator
  //       error.response?.data?.message || //api error
  //         error.message || //network error
  //         "Failed to fetch products! Please try again!!"
  //     ); //extract error msg if available
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  // if (loading) {
  //   return (
  //     <div className="flex items-center justify-center min-h-screen">
  //       <span className="text-xl font-semibold">Loading products...</span>
  //     </div>
  //   );
  // }

  // if (error) {
  //   return (
  //     <div className="flex items-center justify-center min-h-screen">
  //       <span className="text-xl text-red-500">Error: {error}</span>
  //     </div>
  //   );
  // }

  //useLocation() -> allow developers to track URL changes | conditionally render UI elements based on the path & search parameters
  // const location = useLocation();
  // const username = location.state?.username; //?. is called optional chaining operator -> safely accesses nested properties without throwing errors if something in the chain is null or undefined
  // const path = location.pathname;
  // console.log(username);
  // console.log(path);

  const products = useLoaderData();

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
