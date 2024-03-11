import { createTheme, responsiveFontSizes } from '@mui/material/styles';

declare module '@mui/material/styles' {
  interface Palette {
    textLight: Palette['text'];
  }
  interface PaletteOptions {
    textLight: PaletteOptions['text'];
  }
}

const baseTheme = createTheme({
  palette: {
    primary: {
      main: '#8B3DFF',
    },
    secondary: {
      main: '#E5E7ED',
    },
    background: {
      default: '#ffffff',
    },
    text: {
      primary: '#2A3238',
      secondary: '#ffffff',
    },
    textLight: {
      primary: '#707477',
      secondary: '#959596',
      disabled: '#D3D4D4',
    },
    grey: {
      '100': '#BFC4C8',
      '200': '#959596',
      '300': '#343536',
      '400': '#252627',
      '500': '#18191B',
      '600': '#F4F5F5',
      '700': '#F0F0F0',
      '800': '#D9D9D9',
      '900': '#E0E0E0',
    },

    common: {
      black: '#000000',
    },
  },
  typography: {
    fontFamily: 'Manrope',

    body1: {
      fontSize: 16,
      fontWeight: 600,
      lineHeight: 1.6,
      letterSpacing: 0.2,
    },

    caption: {
      fontSize: 11,
      fontWeight: 700,
      lineHeight: 1.8,
      letterSpacing: 0.2,
    },
  },
  components: {
    MuiTypography: {
      defaultProps: {},
      styleOverrides: {
        root: {
          textTransform: 'none',
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
        },
      },
      defaultProps: {
        disableElevation: true,
        disableRipple: true,
      },
    },
  },
});

const responsiveFontTheme = responsiveFontSizes(baseTheme);

export default responsiveFontTheme;
