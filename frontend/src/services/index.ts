import { ApolloClient, InMemoryCache, gql } from "@apollo/client";
import { CONSTANTS } from "../constants";

export const client = new ApolloClient({
  uri: process.env.REACT_APP_BASE_URL,
  cache: new InMemoryCache(),
  defaultOptions: {
    watchQuery: {
      fetchPolicy: "no-cache",
    },
    query: {
      fetchPolicy: "no-cache",
    },
  },
});

export const getGraphqlData = async (query: string, variables?: any) => {
  try {
    const { data } = await client.query({
      query: gql`
        ${query}
      `,
      variables,
      context: {
        headers: {
          Authorization: process.env.REACT_APP_SUBSCRIPTION_KEY,
        },
      },
    });
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

export const setGraphqlData = async (mutation: string, variables?: any) => {
  try {
    const { data } = await client.mutate({
      mutation: gql`
        ${mutation}
      `,
      variables,
      context: {
        headers: {
          Authorization: process.env.REACT_APP_SUBSCRIPTION_KEY,
        },
      },
    });
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

export const getTableData = async (
  tableName: string,
  page: number,
  pageSize: number
) => {
  try {
    const variables = {
      tableName: tableName,
      limit: pageSize,
      offset: pageSize * page,
    };
    let response = await getGraphqlData(CONSTANTS.QUERY_TABLEDATA, variables);
    return convertTableData(response);
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const convertTableData = (response: any) => {
  const headers = response.headers.map((header: any) => header.name);
  const rows = response.rows.data.map((row: any) => {
    const newRow: { [key: string]: any } = {};
    row.forEach((cell: any, index: number) => {
      newRow[headers[index]] = cell.value;
    });
    return newRow;
  });
  return {
    data: rows,
    total: response.rows.pagination.totalRecords,
  };
};
