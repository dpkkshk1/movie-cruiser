import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies: Array<Movie>;
  useWatchlistApi: boolean = true;
  constructor(private movieService: MovieService,private snackBar:MatSnackBar) {
    this.movies = [];
  }

  ngOnInit() {
    this.movieService.getMovieFromWatchlist().subscribe((movies) => {
      this.movies.push(...movies);
      if(this.movies.length==0){
        this.snackBar.open("No Movie Added to WatchList", "", {
          duration: 1000
        });
      }
    });
  }

}
