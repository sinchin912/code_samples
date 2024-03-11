import { Meta, StoryObj } from '@storybook/react';
import DataGrid from '.';
import React from 'react';
import {
  GridColDef,
  GridRowModel,
  GridRowsProp,
} from '@mui/x-data-grid';

const meta: Meta<typeof DataGrid> = {
  component: DataGrid,
};
export default meta;

type Story = StoryObj<typeof DataGrid>;

const rows: GridRowsProp = [
  { id: 1, col1: 'Hello', col2: 'World' },
  { id: 2, col1: 'DataGridPro', col2: 'is Awesome' },
  { id: 3, col1: 'MUI', col2: 'is Amazing' },
];

const columns: GridColDef[] = [
  {
    field: 'col1',
    headerName: 'Column 1',
    editable: true,
  },
  {
    field: 'col2',
    headerName: 'Column 2',
    editable: true,
  },
];

const updateRow = async (newRow: GridRowModel) => {
  const response = await new Promise((resolve, reject) => {
    resolve({ ...newRow });
  });
  return response;
};

export const DataGridComponent: Story = {
  args: {
    updateRow,
  },
};
