import * as React from "react";
import {
  DataGrid,
  GridRowsProp,
  GridColDef,
  GridValidRowModel,
  GridToolbarContainer,
  GridRowModes,
  GridRowModesModel,
  GridEventListener,
  GridRowEditStopReasons,
  GridActionsCellItem,
  GridRowId,
} from "@mui/x-data-grid";
import { Snackbar, Alert, AlertProps, Button } from "@mui/material";
import { CONSTANTS } from "../../../constants";
import AddIcon from "@mui/icons-material/Add";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";
import CancelIcon from "@mui/icons-material/Close";
import { getTableData } from "../../../services";

const constant = CONSTANTS.TABLE;
interface IDataGridProps {
  selectedTableName: string;
  updateRow: (newRow: GridValidRowModel) => Promise<any>;
}

interface EditToolbarProps {
  setRows: (newRows: (oldRows: GridRowsProp) => GridRowsProp) => void;
  setRowModesModel: (
    newModel: (oldModel: GridRowModesModel) => GridRowModesModel
  ) => void;
}

const EditToolbar = (props: EditToolbarProps) => {
  const { setRows, setRowModesModel } = props;

  const handleClick = () => {
    const id = 10000;
    setRows((oldRows) => [...oldRows, { id }]);
    setRowModesModel((oldModel) => ({
      ...oldModel,
      [id]: { mode: GridRowModes.Edit, fieldToFocus: "id" },
    }));
  };

  return (
    <GridToolbarContainer>
      <Button color="primary" onClick={handleClick} startIcon=<AddIcon />>
        {constant.ADD_RECORD}
      </Button>
    </GridToolbarContainer>
  );
};

const DataGridTable: React.FC<IDataGridProps> = ({
  updateRow,
  selectedTableName,
}) => {
  const [snackbar, setSnackbar] = React.useState<Pick<
    AlertProps,
    "children" | "severity"
  > | null>(null);
  const [paginationModel, setPaginationModel] = React.useState({
    page: 0,
    pageSize: 10,
  });
  const [rows, setRows] = React.useState<GridRowsProp>([]);
  const [cols, setCols] = React.useState<GridColDef[]>([]);
  const [loading, setLoading] = React.useState(false);
  const [rowCount, setRowCount] = React.useState(0);
  const [rowModesModel, setRowModesModel] = React.useState<GridRowModesModel>(
    {}
  );

  const isTableEditable =
    !CONSTANTS.NON_EDITABLE_TABLES.includes(selectedTableName);

  const handleRowEditStop: GridEventListener<"rowEditStop"> = (
    params,
    event
  ) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const handleEditClick = (id: GridRowId) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.Edit, fieldToFocus: "id" },
    });
  };

  const handleSaveClick = (id: GridRowId) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View },
    });
  };

  const handleCancelClick = (id: GridRowId) => () => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });
    const editedRow = rows.find((row) => row.id === id);
    if (editedRow!.isNew) {
      setRows(rows.filter((row) => row.id !== id));
    }
  };

  const handleCloseSnackbar = () => setSnackbar(null);

  const processRowUpdate = async (newRow: GridValidRowModel) => {
    await updateRow(newRow);
    setSnackbar({
      children: CONSTANTS.SUCCESS_MSG,
      severity: "success",
    });
    const updatedRow = { ...newRow, isNew: false };
    setRows(rows.map((row) => (row.id === newRow.id ? updatedRow : row)));
    return updatedRow;
  };

  const handleProcessRowUpdateError = React.useCallback((error: Error) => {
    setSnackbar({ children: error.message, severity: "error" });
  }, []);

  const handleRowModesModelChange = (newRowModesModel: GridRowModesModel) => {
    setRowModesModel(newRowModesModel);
  };

  const handleCellDoubleClick: GridEventListener<"rowDoubleClick"> = (
    params,
    event
  ) => {
    event!.defaultMuiPrevented = true;
  };

  React.useEffect(() => {
    setPaginationModel({
      page: 0,
      pageSize: 10,
    });
  }, [selectedTableName]);

  React.useEffect(() => {
    (async () => {
      setLoading(true);
      const data =
        selectedTableName !== ""
          ? await getTableData(
              selectedTableName,
              paginationModel.page,
              paginationModel.pageSize
            )
          : { data: [], total: 0 };

      const newRows = data!.data;
      const columns =
        newRows.length > 0
          ? Object.keys(newRows[0]).map((column) => ({
              field: column,
              headerName: column,
              width: 200,
              editable: isTableEditable,
            }))
          : [];

      setCols([...columns]);

      setRows(
        newRows.map((row: any) => ({
          ...row,
          items:
            typeof row.items === "object"
              ? JSON.stringify(row.items)
              : row.items,
        }))
      );
      setRowCount(+data!.total);
      setLoading(false);
    })();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [paginationModel.page, paginationModel.pageSize, selectedTableName]);

  return (
    <div>
      <DataGrid
        rows={rows}
        columns={
          isTableEditable
            ? [
                ...cols,
                {
                  field: "actions",
                  type: "actions",
                  headerName: "Actions",
                  width: 100,
                  cellClassName: "actions",
                  getActions: ({ id }) => {
                    const isInEditMode =
                      rowModesModel[id]?.mode === GridRowModes.Edit;
                    if (isInEditMode) {
                      return [
                        <GridActionsCellItem
                          icon={<SaveIcon />}
                          label="Save"
                          sx={{
                            color: "primary.main",
                          }}
                          onClick={handleSaveClick(id)}
                        />,
                        <GridActionsCellItem
                          icon={<CancelIcon />}
                          label="Cancel"
                          className="textPrimary"
                          onClick={handleCancelClick(id)}
                          color="inherit"
                        />,
                      ];
                    }
                    return [
                      <GridActionsCellItem
                        icon={<EditIcon />}
                        label="Edit"
                        className="textPrimary"
                        onClick={handleEditClick(id)}
                        color="inherit"
                      />,
                    ];
                  },
                },
              ]
            : [...cols]
        }
        rowCount={rowCount}
        paginationMode="server"
        paginationModel={paginationModel}
        onPaginationModelChange={setPaginationModel}
        loading={loading}
        processRowUpdate={processRowUpdate}
        onProcessRowUpdateError={handleProcessRowUpdateError}
        autoHeight
        pageSizeOptions={[5, 10, 25]}
        slots={{
          toolbar: EditToolbar,
        }}
        slotProps={{
          toolbar: { setRows, setRowModesModel },
        }}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        onCellDoubleClick={handleCellDoubleClick}
      />
      {!!snackbar && (
        <Snackbar
          open
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
          onClose={handleCloseSnackbar}
          autoHideDuration={6000}
        >
          <Alert {...snackbar} onClose={handleCloseSnackbar} />
        </Snackbar>
      )}
    </div>
  );
};

export default DataGridTable;
