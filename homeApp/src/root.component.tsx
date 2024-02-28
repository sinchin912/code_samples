import { Box, List, ListItem, Typography } from "@mui/material";
import { deepPurple, grey } from "@mui/material/colors";
import SwipeableViews from "react-swipeable-views";
import { autoPlay } from "react-swipeable-views-utils";

const AutoPlaySwipeableViews = autoPlay(SwipeableViews);
import Movies from "../assets/images/movies.jpg";
import Series from "../assets/images/series.jpg";
import Shows from "../assets/images/shows.webp";

export default function Root(props) {
  return (
    <Box
      sx={{
        p: 4,
        display: "flex",
      }}
    >
      <Box sx={{ flex: 1 }}>
        <Typography variant="h2" sx={{ color: deepPurple[50] }}>
          Welcome
        </Typography>
        <Typography variant="body1" sx={{ color: grey[50], my: 4 }}>
          Welcome to our corner of the internet where we dive deep into the
          realms of movies, TV shows, and web series! Whether you're a casual
          viewer or a die-hard fan, we've got something for everyone. From
          blockbuster hits to hidden gems, we cover it all. Sit back, relax, and
          let us be your guide through the wonderful world of entertainment.
        </Typography>
        <List sx={{ my: 4 }}>
          <ListItem>
            <Typography variant="h5" sx={{ color: grey[50] }}>
              Latest Reviews: Stay up-to-date with our insightful reviews of the
              newest releases. We'll give you the lowdown on what's hot and
              what's not, so you can spend your time wisely.
            </Typography>
          </ListItem>
          <ListItem>
            <Typography variant="h5" sx={{ color: grey[50] }}>
              Recommendations: Not sure what to watch next? We've got you
              covered with personalized recommendations tailored to your tastes.
              Whether you're in the mood for comedy, drama, or something in
              between, we'll help you find your next binge-worthy obsession.
            </Typography>
          </ListItem>
          <ListItem>
            <Typography variant="h5" sx={{ color: grey[50] }}>
              Community Interaction: Join our community of fellow movie and TV
              enthusiasts! Share your thoughts, engage in lively discussions,
              and connect with like-minded individuals who share your passion
              for entertainment.
            </Typography>
          </ListItem>
        </List>
        <Typography variant="body1" sx={{ color: grey[50], my: 3 }}>
          So grab your popcorn, settle into your favorite spot on the couch, and
          let the entertainment begin! With CouchPotato, the adventure never
          ends.
        </Typography>
      </Box>
      <Box sx={{ flex: 1 }}>
        <AutoPlaySwipeableViews>
          <div key="movies">
            <Box
              component="img"
              sx={{
                height: 700,
                display: "block",
                maxWidth: 800,
                overflow: "hidden",
                width: "100%",
                mx: "auto",
              }}
              src={Movies}
              alt="Top Movies"
            />
          </div>
          <div key="series">
            <Box
              component="img"
              sx={{
                height: 700,
                display: "block",
                maxWidth: 800,
                overflow: "hidden",
                width: "100%",
                mx: "auto",
              }}
              src={Series}
              alt="Top Series"
            />
          </div>
          <div key="shows">
            <Box
              component="img"
              sx={{
                height: 700,
                display: "block",
                maxWidth: 800,
                overflow: "hidden",
                width: "100%",
                mx: "auto",
              }}
              src={Shows}
              alt="Top Shows"
            />
          </div>
        </AutoPlaySwipeableViews>
      </Box>
    </Box>
  );
}
