export const CONSTANTS = {
  TABLE: {
    TITLE: "Tables",
    ADD_RECORD: "Add Record",
  },
  NO_ROWS: "No rows found",
  SUCCESS_MSG: "Data successfully saved",
  TITLE: "DB Visualizer",
  NAV_ITEMS: {
    HOME: "Home",
    CONTACT_US: "Contact us",
    DOCUMENT: "Document",
    TABLES: "Tables",
  },
  NON_EDITABLE_TABLES: ["user", "contract"],
  QUERY_TABLENAME: `
  query {
    list {
      name
    }
  }
`,
  QUERY_TABLEDATA: `
query Tables($tableName: String!, $limit: Int, $offset: Int) {
  headers(tableName: $tableName) {
    name
    type
  }
  rows(tableName: $tableName, limit: $limit, offset: $offset) {
    data {
      name
      type
      value
    }
    pagination {
      nextPage
      totalRecords
    }
  }
}
`,
  MUTATION_TABLEINSERT: `
mutation AddData(
  $tableName: String!
  $columnNames: String!
  $columnValues: String!
) {
  addData(
    tableName: $tableName
    columnNames: $columnNames
    columnValues: $columnValues
  )
}
`,
  MUTATION_TABLEUPDATE: `
  mutation UpdateData(
    $tableName: String!
    $updateValue: String!
    $condition: String!
  ) {
    updateData(
      tableName: $tableName
      updateValue: $updateValue
      condition: $condition
    )
  }
`,
};
