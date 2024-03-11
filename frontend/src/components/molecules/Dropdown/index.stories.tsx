import type { Meta, StoryObj } from "@storybook/react";
import Dropdown from ".";
import { action } from "@storybook/addon-actions";

const meta: Meta<typeof Dropdown> = {
  component: Dropdown,
};

export default meta;

type Story = StoryObj<typeof Dropdown>;

export const TableNames: Story = {
  args: {
    value: "",
    options: [
      { value: "Cashkick", label: "Cashkick" },
      { value: "Contracts", label: "Contracts" },
      { value: "Customer", label: "Customer" },
    ],
    label: "Tables",
    handleChange: action("select-dropdown"),
  },
};
