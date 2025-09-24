xquery version "3.1";

declare namespace mr = "http://www.example.com/xquery";
declare variable $baseURI := base-uri();

<mr:author_list>
{
  for $book in doc($baseURI)/library/publisher/book
  return
    <mr:author>
      <mr:name>{ $book/data(author) }</mr:name>
      <mr:title>{ $book/data(title) }</mr:title>
      <mr:price>{$book/data(price)}</mr:price>
    </mr:author>
}
</mr:author_list>