import { Stack, Typography, styled } from '@mui/material';
import * as React from 'react';
import { CONSTANTS } from '../../../constants/index';
import theme from '../../../theme/theme';
import Contacts from '@mui/icons-material/Contacts';
import { Home, Receipt, TableView } from '@mui/icons-material';

const StyledStack = styled(Stack)({
  background: theme.palette.background.default,
  marginRight: theme.spacing(3),
  borderRadius: theme.spacing(1),
});

const NavItems = (icon: React.ReactElement, item: string) => {
  return (
    <Stack direction="row" spacing={1} alignItems="center">
      {icon}
      <Typography variant="h6">{item}</Typography>
    </Stack>
  );
};
const constants = CONSTANTS.NAV_ITEMS;
const Navbar = () => {
  return (
    <StyledStack justifyContent="space-between" alignItems="center" height="100%">
      <Stack spacing={4}>
        <Stack padding={theme.spacing(3)}>
          <Typography variant="h4">{CONSTANTS.TITLE}</Typography>
        </Stack>
        <Stack spacing={3}>
          {NavItems(<Home />, constants.HOME)}
          {NavItems(<TableView />, constants.TABLES)}
          {NavItems(<Receipt />, constants.DOCUMENT)}
          {NavItems(<Contacts />, constants.CONTACT_US)}
        </Stack>
      </Stack>
    </StyledStack>
  );
};

export default Navbar;
