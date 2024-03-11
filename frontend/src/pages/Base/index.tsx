import { Grid, SelectChangeEvent, Stack, styled } from "@mui/material";
import Dropdown from "../../components/molecules/Dropdown";
import { useEffect, useState } from "react";
import { CONSTANTS } from "../../constants/index";
import DataGridTable from "../../components/organisms/DataGrid";
import { GridValidRowModel } from "@mui/x-data-grid";
import Navbar from "../../components/organisms/Navbar";
import theme from "../../theme/theme";
import { getGraphqlData, setGraphqlData } from "../../services";
import { util } from "../../utils/app.utils";

export interface TableProps {
  name: string;
}

const StyledGrid = styled(Grid)({
  background: theme.palette.secondary.main,
  margin: "-8px",
  padding: theme.spacing(3),
});

const StyledStack = styled(Stack)({
  background: theme.palette.background.default,
  padding: theme.spacing(3),
  borderRadius: theme.spacing(1),
});

const BasePage: React.FC = () => {
  const [tableNames, setTableNames] = useState<
    { label: string; value: string }[]
  >([]);
  const [selectedTableName, setSelectedTableName] = useState<string>("");

  useEffect(() => {
    const fetchTableNames = async () => {
      try {
        const fetchedTableNames = await getGraphqlData(
          CONSTANTS.QUERY_TABLENAME
        );
        setTableNames(
          fetchedTableNames.list.map((tableName: TableProps) => ({
            label: tableName.name,
            value: tableName.name,
          }))
        );
      } catch (error) {
        console.error("Error fetching table names:", error);
      }
    };

    fetchTableNames();
  }, []);

  const handleTableNameChange = (event: SelectChangeEvent) => {
    setSelectedTableName(event.target.value as string);
  };

  const updateRow = async (newRow: GridValidRowModel) => {
    console.log();
    let response;

    if (newRow.hasOwnProperty("items")) {
      const variables = {
        tableName: selectedTableName,
        updateValue: util.jsonStringToCommaSeparatedMap(JSON.stringify(newRow)),
        condition: util.jsonStringToCondition(JSON.stringify(newRow)),
      };
      response = await setGraphqlData(
        CONSTANTS.MUTATION_TABLEUPDATE,
        variables
      );
    } else {
      const variables = {
        tableName: selectedTableName,
        columnNames: util.jsonStringToCommaSeparatedKeys(
          JSON.stringify(newRow)
        ),
        columnValues: util.jsonStringToCommaSeparatedValues(
          JSON.stringify(newRow)
        ),
      };
      await setGraphqlData(CONSTANTS.MUTATION_TABLEINSERT, variables);
      response = undefined;
    }
    return response;
  };

  return (
    <StyledGrid container justifyContent="space-between">
      <Grid item xs={3} height="95vh">
        <Navbar />
      </Grid>
      <Grid item xs={9} height="95vh">
        <StyledStack spacing={4} height="95%">
          <Dropdown
            options={tableNames}
            label={CONSTANTS.TABLE.TITLE}
            value={selectedTableName}
            handleChange={handleTableNameChange}
          />
          <DataGridTable
            updateRow={updateRow}
            selectedTableName={selectedTableName}
          />
        </StyledStack>
      </Grid>
    </StyledGrid>
  );
};

export default BasePage;
