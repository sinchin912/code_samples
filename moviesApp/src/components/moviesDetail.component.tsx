import { Avatar, Box, Button, Stack, Typography } from "@mui/material";
import { getMovieDetails } from "../utils/movies.service";
import { MovieProps } from "../interfaces/movies.interface";
import { deepPurple, grey } from "@mui/material/colors";
import { useParams } from "react-router-dom";

export const MoviesDetail = () => {
  const params = useParams();
  const movieDetails: MovieProps = getMovieDetails(
    parseInt(params.id),
    params.section
  );

  return (
    <Stack
      sx={{
        display: "flex",
        flexDirection: "column",
        p: 2,
      }}
    >
      <Stack
        sx={{
          display: "flex",
          flexDirection: "row",
        }}
      >
        <Avatar
          sx={{
            width: "360px",
            height: "520px",
            objectFit: "cover",
            borderRadius: "10px",
          }}
          src={movieDetails.url}
        />

        <Box sx={{ flex: 1, color: grey[50], p: 2 }}>
          <Typography variant="h1" sx={{ my: 4 }}>
            {movieDetails.title}
          </Typography>
          <Typography variant="h2">IMDB : {movieDetails.rating}/10</Typography>
        </Box>
        <Button
          variant="contained"
          href={`/${params.section}`}
          sx={{
            height: "fit-content",
            bgcolor: deepPurple[500],
            color: grey[100],
            "&:hover": {
              bgcolor: deepPurple[500],
            },
          }}
        >
          Back to List
        </Button>
      </Stack>
      <Box sx={{ p: 2 }}>
        <Typography variant="h6" sx={{ color: grey[50] }}>
          {movieDetails.describe}
        </Typography>
      </Box>
    </Stack>
  );
};
