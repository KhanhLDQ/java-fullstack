import { useEffect, useReducer } from "react";
import { AuthContext } from "./auth";

//actions
const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGOUT = "LOGOUT";

const authReducer = (prevState, action) => {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return {
        ...prevState,
        jwt: action.payload.jwt,
        user: action.payload.user,
        isAuthenticated: true,
      };
    case LOGOUT:
      return {
        ...prevState,
        jwt: null,
        user: null,
        isAuthenticated: false,
      };
    default:
      return prevState;
  }
};

export const AuthProvider = ({ children }) => {
  const initialAuthState = (() => {
    try {
      const jwt = localStorage.getItem("jwt");
      const user = localStorage.getItem("user");

      if (jwt && user) {
        return {
          jwt,
          user: JSON.parse(user),
          isAuthenticated: true,
        };
      }
    } catch (error) {
      console.error("Failed to load auth state from localStorage: ", error);
    }

    return {
      jwt: null,
      user: null,
      isAuthenticated: false,
    };
  })();

  const [authState, dispatch] = useReducer(authReducer, initialAuthState);

  useEffect(() => {
    try {
      if (authState.isAuthenticated) {
        localStorage.setItem("jwt", authState.jwt);
        localStorage.setItem("user", JSON.stringify(authState.user));
      } else {
        localStorage.removeItem("jwt");
        localStorage.removeItem("user");
      }
    } catch (error) {
      console.error("Failed to save auth state to localStorage: ", error);
    }
  }, [authState]);

  const loginSuccess = (jwt, user) => {
    dispatch({ type: LOGIN_SUCCESS, payload: { jwt, user } });
  };

  const logout = () => {
    dispatch({ type: LOGOUT });
  };

  return (
    <AuthContext.Provider
      value={{
        jwt: authState.jwt,
        user: authState.user,
        isAuthenticated: authState.isAuthenticated,
        loginSuccess,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
