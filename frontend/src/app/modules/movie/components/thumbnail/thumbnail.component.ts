import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';
@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;
  @Input()
  useWatchlistApi: boolean;
  @Output()
  addMovie = new EventEmitter();
  @Output()
  deleteMovie = new EventEmitter();

  constructor(private snackBar: MatSnackBar, public dialog: MatDialog) {


  }

  ngOnInit() {
  }
  addToWatchlist(movie) {
    this.addMovie.emit(movie);
  }
  deleteFromWatchlist(movie) {
    this.deleteMovie.emit(movie);
  }
  updateFromWatchlist(actionType) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data: { obj: this.movie, actionType: actionType }
    });
    dialogRef.afterClosed().subscribe((reslut) => {

    })
  }
}
