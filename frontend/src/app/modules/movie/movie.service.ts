import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Movie } from './movie';
import { map, retry } from 'rxjs/operators';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MovieService {
  tmdbEndPoint: string;
  imageprefix: string;
  apiKey: string;
  watchlistEnd: string;
  springEndPoint: string;
  search:string;
  constructor(private http: HttpClient) {
    this.apiKey = "api_key=d4cd7e4967199ae047592ed16f484bdd";
    this.tmdbEndPoint = "https://api.themoviedb.org/3/movie";
    this.imageprefix = "https://image.tmdb.org/t/p/w500";
    this.watchlistEnd = "http://localhost:3000/watchList";
    this.springEndPoint = "http://localhost:8084/api/v1/movieservice"
    this.search = "https://api.themoviedb.org/3/search/movie?";
  }

  searchMovies(searchKey:string): Observable<Array<Movie>>{
    if(searchKey.length>0){
      const url = `${this.search}${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
      return this.http.get(url).pipe(
        retry(3),
        map(this.pickMovieResult),
        map(this.transformPosterPath.bind(this))
      );
    }
  }

  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const endPoint = `${this.tmdbEndPoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickMovieResult),
      map(this.transformPosterPath.bind(this))
    );
  }
  transformPosterPath(movies): Array<Movie> {
    return movies.map(movie => {

      movie.poster_path = `${this.imageprefix}${movie.poster_path}`

      return movie;
    })
  }
  pickMovieResult(response) {
    return response['results'];
  }

  addMovieToWatchlist(movie) {
    const url = `${this.springEndPoint}/movie`;
    return this.http.post(url, movie);
  }
  getMovieFromWatchlist(): Observable<Array<Movie>> {
    const url = `${this.springEndPoint}/movies`;
    return this.http.get<Array<Movie>>(url);
  }

  deleteFromWatchlist(movie: Movie) {
    const url = `${this.springEndPoint}/movie/${movie.id}`;
    return this.http.delete(url, { responseType: 'text' });
  }
  updateComments(movie) {
    const url = `${this.springEndPoint}/movie/${movie.id}`;
    return this.http.put(url, movie);
  }
}
