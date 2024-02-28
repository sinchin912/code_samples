import {
  Box,
  List,
  ListItem,
  ListItemButton,
  Stack,
  Typography,
} from "@mui/material";
import { deepPurple, grey } from "@mui/material/colors";
import FacebookIcon from "@mui/icons-material/Facebook";
import TwitterIcon from "@mui/icons-material/Twitter";
import LinkedInIcon from "@mui/icons-material/LinkedIn";

export default function Root(props) {
  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        backgroundColor: grey[800],
        color: grey[50],
      }}
    >
      <Typography variant="h6" sx={{ mx: 2 }}>
        @ Copyright 2024
      </Typography>
      <Stack sx={{ flex: 1, alignItems: "center" }}>
        <Typography variant="subtitle1">v1.0.0</Typography>
      </Stack>
      <List sx={{ display: "flex", mx: 1 }}>
        <ListItem>
          <ListItemButton
            target="_blank"
            href="https://facebook.com"
            sx={{ p: 0 }}
          >
            <FacebookIcon
              sx={{
                backgroundColor: grey[50],
                borderRadius: "4px",
                color: deepPurple[500],
              }}
            />
          </ListItemButton>
        </ListItem>
        <ListItem>
          <ListItemButton
            target="_blank"
            href="https://twitter.com"
            sx={{ p: 0 }}
          >
            <TwitterIcon
              sx={{
                backgroundColor: grey[50],
                borderRadius: "4px",
                color: deepPurple[500],
              }}
            />
          </ListItemButton>
        </ListItem>
        <ListItem>
          <ListItemButton
            target="_blank"
            href="https://linkedin.com"
            sx={{ p: 0 }}
          >
            <LinkedInIcon
              sx={{
                backgroundColor: grey[50],
                borderRadius: "4px",
                color: deepPurple[500],
              }}
            />
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  );
}
