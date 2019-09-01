import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  movies: Array<Movie>;
  @Input()
  useWatchlistApi: boolean;
  constructor(private movieService: MovieService, private snackBar: MatSnackBar) {
  }

  ngOnInit() { }
  addToWatchlist(movie) {
    const message = `${movie.title} add to watchlist`;
    this.movieService.addMovieToWatchlist(movie).subscribe(() => {
      this.snackBar.open(message, "", {
        duration: 1000
      })
    });
  }
  deleteFromWatchlist(movie) {
    const message = `${movie.title} deleted from watchlist`;
    this.movieService.deleteFromWatchlist(movie).subscribe((res) => {
      this.snackBar.open(message, "", {
        duration: 1000
      });
    });
    const index = this.movies.indexOf(movie);
    this.movies.splice(index,1);
  }

}
