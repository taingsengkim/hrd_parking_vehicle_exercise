


void main() {
    Author author1 = new Author(1,UUID.randomUUID().toString(),"David Corenswet","profile.url");
    Author author2 = new Author(2,UUID.randomUUID().toString(),"Kim","profile.url");


    Movie movie = new Movie(1,UUID.randomUUID().toString(),"Superman 2025","new dc movies",LocalDate.of(2025,3,10),Set.of(new Author[]{author2, author1}),"2h35mn",Set.of(new String[]{"Smart","Cellcard"}),false);


    System.out.println(movie);
}
