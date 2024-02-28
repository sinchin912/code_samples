import { Avatar, Box, Link, Stack, Typography } from "@mui/material";
import { grey } from "@mui/material/colors";
import { getMovies } from "../utils/movies.service";
import { MovieProps } from "../interfaces/movies.interface";
import { useParams } from "react-router-dom";

export const MoviesList = () => {
  const params = useParams();
  const movies: MovieProps[] = getMovies(params.section);
  return (
    <Stack
      sx={{
        display: "flex",
        flexFlow: "row wrap",
        alignItems: "center",
        justifyContent: "space-around",
      }}
    >
      {movies.map((movie) => (
        <Link underline="none" href={`/${params.section}/${movie.id}`}>
          <Box
            sx={{
              color: grey[50],
              m: 2,
              p: 2,
              width: "250px",
              backgroundColor: grey[700],
              borderRadius: "10px",
            }}
          >
            <Avatar
              sx={{
                width: "180px",
                height: "260px",
                objectFit: "cover",
                borderRadius: "10px",
                mx: "auto",
              }}
              key={movie.id}
              src={movie.url}
            />
            <Typography variant="h4" sx={{ my: 1, wordWrap: "break-word" }}>
              {movie.title}
            </Typography>
            <Typography>IMDB Rating: {movie.rating}/10</Typography>
          </Box>
        </Link>
      ))}
      ;
    </Stack>
  );
};
