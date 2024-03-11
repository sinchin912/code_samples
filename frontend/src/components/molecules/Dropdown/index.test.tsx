import { fireEvent, render, screen } from "@testing-library/react";
import React from "react";
import Dropdown from ".";

const mockHandleChange = jest.fn();

const options = [
  { value: "Contract", label: "Contract" },
  { value: "Cashkick", label: "Cashkick" },
  { value: "Payment", label: "Payment" },
];

const renderDropdown = () => {
  render(
    <Dropdown
      options={options}
      label={"Tables"}
      value={"Contract"}
      handleChange={mockHandleChange}
    />
  );
};

describe("Dropdown tests", () => {
  it("renders Dropdown component", () => {
    renderDropdown();
    expect(screen.getByTestId("dropdown")).toBeInTheDocument();
    const form = screen.getByRole("combobox");
    expect(form).toBeDefined();
    expect(screen.getByTestId("dropdown")).not.toHaveClass("Mui-focused");
    fireEvent.mouseDown(form);
  });

  it("displays the selected option correctly", () => {
    renderDropdown();
    expect(screen.getByText("Contract")).toBeInTheDocument();
  });
});
