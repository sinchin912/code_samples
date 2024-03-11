import * as React from "react";
import { MenuItem, Typography, FormControl } from "@mui/material";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import theme from "../../../theme/theme";

export interface DropdownProps {
  options: { label: string; value: string }[];
  label: string;
  value: string;
  // eslint-disable-next-line no-unused-vars
  handleChange: (event: SelectChangeEvent) => void;
}

const Dropdown: React.FC<DropdownProps> = ({
  options,
  label,
  value,
  handleChange,
}) => {
  const [open, setOpen] = React.useState(false);

  return (
    <FormControl data-testid="dropdown" sx={{ width: "30vw" }}>
      <Select
        value={value}
        onChange={handleChange}
        renderValue={() => (value === "" ? label : value)}
        displayEmpty
        onOpen={() => setOpen(true)}
        onClose={() => setOpen(false)}
        sx={{
          height: "36px",
          padding: "3px 4px",
          "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
            borderColor: theme.palette.grey[100],
            borderWidth: "1px",
          },
        }}
        SelectDisplayProps={{ style: { paddingRight: "7px" } }}
        MenuProps={{
          MenuListProps: {
            sx: {
              minWidth: "250px",
              marginTop: "4px",
              borderRadius: "4px",
              border: `1px solid ${theme.palette.grey[100]}`,
            },
          },
        }}
      >
        {options?.map((option) => {
          return (
            <MenuItem
              data-testid="options"
              value={option?.value}
              key={option?.value}
              sx={{
                color: theme.palette.text.primary,
              }}
            >
              <Typography variant="body1">{option?.label}</Typography>
            </MenuItem>
          );
        })}
      </Select>
    </FormControl>
  );
};

export default Dropdown;
