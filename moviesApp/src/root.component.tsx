import { BrowserRouter, Route, Routes } from "react-router-dom";
import { MoviesList } from "./components/moviesList.component";
import { MoviesDetail } from "./components/moviesDetail.component";

export default function Root(props) {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/:section/:id" element={<MoviesDetail />} />
        <Route path="/:section/*" element={<MoviesList />} />
      </Routes>
    </BrowserRouter>
  );
}
