# cloud-itonami-iso3166-tuv

**`:implemented`** for **TUV** (Tuvalu): an "Actors" pattern market-entry /
public-procurement compliance service (Governor + LLM advisor + langgraph-clj
StateGraph + append-only audit ledger + Store), adapted from the
`cloud-itonami-iso3166-ago` reference implementation.

Flagship check: `business-registration-missing` (Companies and Business
Registration Act, CAP 40.12, registration / Operational License process).
**Six** governor checks, not the AGO reference's seven -- see
`src/marketentry/governor.cljc` for why.

```
clojure -M:dev:test
```

## Grounding

This actor's dossier is intentionally **thinner and more conservative** than
several sibling jurisdictions in this actor family, an honest reflection of
Tuvalu's limited digitized government information availability (the same
discipline `cloud-itonami-iso3166-stp`, `cloud-itonami-iso3166-sdn` and
`cloud-itonami-iso3166-gnb` already established). What IS verified (via
`finance.gov.tv` and `tuvalu-legislation.tv`, official Government of Tuvalu
domains, plus a WIPO Lex mirror):

- **Procurement regulatory body**: a Central Procurement Unit, established
  within the ministry responsible for finance (the Ministry of Finance and
  Economic Development).
- **Procurement legal basis**: the Public Procurement Act, CAP 4.21 (2022
  Revised Edition; original enactment appears to be 2014).
- **Business/commercial registration**: the Companies and Business
  Registration Act, Chapter 40.12 (2008 Revised Edition), administered by
  the Ministry of Finance.
- **Registration process**: a "Business, Revenue & Customs Registration"
  form submitted to `business@gov.tv`; once approved, the operator applies
  for an Operational License.

What is NOT verified, and is therefore NOT claimed: a national
e-procurement portal, and a tax-ID/corporate-number scheme distinct from
ordinary business registration. See `docs/business-model.md` and
`docs/operator-guide.md` for the full grounding notes.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Tuvalu:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
