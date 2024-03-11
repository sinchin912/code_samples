import { ThemeProvider } from "@emotion/react";
import BasePage from "./pages/Base";
import theme from "./theme/theme";
import { ApolloProvider } from "@apollo/client";
import { client } from "./services";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <ApolloProvider client={client}>
        <BasePage />
      </ApolloProvider>
    </ThemeProvider>
  );
}

export default App;
