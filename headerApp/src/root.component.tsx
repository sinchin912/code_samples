import {
  Avatar,
  Box,
  List,
  ListItem,
  ListItemButton,
  Stack,
  Typography,
} from "@mui/material";
import Logo from "../assets/images/logo.svg";
import { deepPurple, grey } from "@mui/material/colors";
import { useLocation } from "react-router-dom";

export default function Root(props) {
  const location = useLocation();

  const isActive = (href) => {
    return location.pathname === href;
  };

  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        backgroundColor: grey[800],
        color: grey[50],
        boxShadow: 1,
      }}
    >
      <Avatar alt="logo" src={Logo} variant="rounded" sx={{ mx: 2 }} />
      <Typography variant="h4">CouchPotato</Typography>
      <Stack sx={{ flex: 1 }}></Stack>
      <List sx={{ display: "flex", mx: 1 }}>
        <ListItem>
          <ListItemButton
            sx={{
              borderRadius: "8px",
              bgcolor: isActive("/") ? deepPurple[500] : "inherit",
              "&:hover": {
                bgcolor: isActive("/") ? deepPurple[500] : "inherit",
              },
            }}
            href="/"
          >
            Home
          </ListItemButton>
        </ListItem>
        <ListItem>
          <ListItemButton
            sx={{
              borderRadius: "8px",
              bgcolor: isActive("/movies") ? deepPurple[500] : "inherit",
              "&:hover": {
                bgcolor: isActive("/movies") ? deepPurple[500] : "inherit",
              },
            }}
            href="/movies"
          >
            Movies
          </ListItemButton>
        </ListItem>
        <ListItem>
          <ListItemButton
            sx={{
              borderRadius: "8px",
              bgcolor: isActive("/web-series") ? deepPurple[500] : "inherit",
              "&:hover": {
                bgcolor: isActive("/web-series") ? deepPurple[500] : "inherit",
              },
            }}
            href="/web-series"
          >
            WebSeries
          </ListItemButton>
        </ListItem>
        <ListItem>
          <ListItemButton
            sx={{
              borderRadius: "8px",
              bgcolor: isActive("/tv-shows") ? deepPurple[500] : "inherit",
              "&:hover": {
                bgcolor: isActive("/tv-shows") ? deepPurple[500] : "inherit",
              },
            }}
            href="/tv-shows"
          >
            TvShows
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  );
}
