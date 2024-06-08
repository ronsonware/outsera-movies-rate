COPY movies (movie_year, title, studios, producers, winner)
 FROM 'src/test/resources/data/movielist (2).csv'
 DELIMITER ';'
 CSV HEADER;