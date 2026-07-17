(ns culture.facts
  "Country-level regional-culture catalog for Tuvalu (TUV) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.

  Tuvalu is thinly documented on English Wikipedia; this catalog stays at
  6 entries rather than padding with unverifiable items.")

(def catalog
  "iso3 -> vector of culture entries."
  {"TUV"
   [{:culture/id "tuv.dish.pulaka"
     :culture/name "Pulaka"
     :culture/country "TUV"
     :culture/kind :dish
     :culture/summary "Giant swamp taro (Cyrtosperma merkusii) grown mainly in Tuvalu and an important source of carbohydrates; families maintain ancestral cultivation pits and pass growing techniques through generations, though the crop now faces threats from rising sea levels."
     :culture/url "https://en.wikipedia.org/wiki/Pulaka"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tuv.dish.fekei"
     :culture/name "Fekei"
     :culture/country "TUV"
     :culture/kind :dish
     :culture/summary "Tuvaluan dish made on all the islands, consisting of grated pulaka wrapped in pulaka leaves, steamed, and mixed with coconut cream, per the Wikipedia article on Tuvaluan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Tuvaluan_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tuv.dish.palusami"
     :culture/name "Palusami"
     :culture/country "TUV"
     :culture/kind :dish
     :culture/summary "Also called samoa; served with taro or breadfruit and made of taro leaves, coconut cream, lime juice, onions and spices, per the Wikipedia article on Tuvaluan cuisine, which notes the alternate name reflecting the dish's shared presence in Samoan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Tuvaluan_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tuv.beverage.kava"
     :culture/name "Kava"
     :culture/country "TUV"
     :culture/kind :beverage
     :culture/summary "Listed as Tuvalu's national drink in the infobox of the Wikipedia article on Tuvaluan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Tuvaluan_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tuv.festival.fatele"
     :culture/name "Fatele"
     :culture/country "TUV"
     :culture/kind :festival
     :culture/summary "Traditional dance song of Tuvalu, the most common form of traditional Tuvaluan musical expression; evolved from a seated performance by unmarried women into a modern standing dance performed at community events and celebrations."
     :culture/url "https://en.wikipedia.org/wiki/Fatele"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tuv.heritage.funafuti-conservation-area"
     :culture/name "Funafuti Conservation Area"
     :culture/country "TUV"
     :culture/kind :heritage
     :culture/summary "Marine conservation area covering 33 square kilometers of reef, lagoon and motu (islets) on the western side of Funafuti atoll, established in 1999 and encompassing about one-fifth of the coral reef area of Funafuti lagoon."
     :culture/url "https://en.wikipedia.org/wiki/Funafuti_Conservation_Area"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tuv culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "TUV"))
                 " TUV entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
