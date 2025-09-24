xquery version "3.1";

declare namespace mr = "http://www.example.com/xquery";
declare variable $average := (sum(//price) div count(//price));
declare variable $baseURI := base-uri();

<mr:result>
  <mr:average_price>{$average}</mr:average_price>

  {
    for $book in doc($baseURI)/library/publisher/book[price>$average]
    return
      <mr:expensive_book>
        <mr:title>{ $book/data(title) }</mr:title>
        <mr:current_price>{$book/data(price)}</mr:current_price>
        <mr:price_difference>{$book/data(price) - $average}</mr:price_difference>
      </mr:expensive_book>
  }
</mr:result>