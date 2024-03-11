import { render, screen } from '@testing-library/react';
import React from 'react';
import Navbar from '.';
import { CONSTANTS } from '../../../constants/index';

describe('Dropdown tests', () => {
  it('renders Dropdown component', () => {
    render(<Navbar />);
    expect(screen.getByText(CONSTANTS.TITLE)).toBeInTheDocument();
  });
});
