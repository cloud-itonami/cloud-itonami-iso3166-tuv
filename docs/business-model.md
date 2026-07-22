# Business model — TUV

Independent public-sector market-entry & procurement compliance service for
Tuvalu: helps a market-entry operator assemble and track the evidence a
Public Procurement Act CAP 4.21 filing needs, draft a filing package, and
(with human sign-off) submit it -- never itself an official government
registry or portal.

## What this actor does

1. **Engagement intake** -- records the operator engagement (client,
   jurisdiction, fee terms).
2. **Jurisdiction assessment** -- looks up Tuvalu's own required-evidence
   checklist (`marketentry.facts`) and proposes it, citing the specific
   official source.
3. **Filing draft** -- prepares an unsigned filing-draft record (an
   append-only book-of-record entry, not a real portal submission).
4. **Filing submit** -- prepares the filing-submit record. This is the
   real-world act of actually submitting a registration/filing; it is
   ALWAYS gated on human approval (see Trust Controls below).

## Grounding: what is and is not verified for Tuvalu

This actor's dossier is intentionally **thinner and more conservative**
than several sibling jurisdictions in this actor family (compare
`cloud-itonami-iso3166-ago`, `cloud-itonami-iso3166-fji`). This is a
deliberate, honest reflection of Tuvalu's limited digitized government
information availability, not a defect -- the same discipline
`cloud-itonami-iso3166-stp`, `cloud-itonami-iso3166-sdn` and
`cloud-itonami-iso3166-gnb` already established for this family. What is
verified (via `finance.gov.tv` and `tuvalu-legislation.tv`, official
Government of Tuvalu domains, plus a WIPO Lex mirror):

- A **Central Procurement Unit**, established within the ministry
  responsible for finance (the Ministry of Finance and Economic
  Development), develops public procurement policy, carries out major
  procurement, and performs other functions as prescribed by regulation.
  Source: the ministry's own official site, `finance.gov.tv`.
- The **Public Procurement Act, CAP 4.21 (2022 Revised Edition)** is the
  legal basis (original enactment appears to be 2014). Source: the hosted
  PDF text on `tuvalu-legislation.tv`.
- **Business/commercial registration** runs through the **Companies and
  Business Registration Act, Chapter 40.12 (2008 Revised Edition)**,
  administered by the Ministry of Finance (which also administers the
  Companies Act, the Partnership Act and Foreign Direct Investment Acts).
  Source: `finance.gov.tv`, mirrored at WIPO Lex and the Tuvalu Trade
  Portal.
- **Registration process**: businesses submit a completed "Business,
  Revenue & Customs Registration" form to `business@gov.tv`; once business
  registration is approved, the operator can then apply for an Operational
  License.

What is NOT verified, and is therefore NOT claimed:

- A national transactional e-procurement portal for Tuvalu.
- A tax-ID/corporate-number verification scheme distinct from ordinary
  Companies and Business Registration Act registration. Where tax/revenue
  registration is referenced, it is grounded ONLY in the general "Business,
  Revenue & Customs Registration form" process, not an invented named tax
  authority acronym.
- A resident-representative/local-agent regime distinct from ordinary
  business registration.

## Trust Controls

- **Every jurisdiction requirement this actor states traces to an
  official source cited in `marketentry.facts`.** A proposal that cannot
  cite one is a HARD governor violation (`:no-spec-basis`) -- a false or
  fabricated regulatory-requirement claim is a HARD hold, unconditionally.
- **Any actual filing draft or filing submission requires Market-Entry
  Compliance Governor clearance and always escalates to human sign-off**
  -- `:filing/draft`/`:filing/submit` are permanently absent from every
  rollout phase's auto-commit set (`marketentry.phase`), and the
  governor's own high-stakes gate (`marketentry.governor`) independently
  enforces the same rule. Two layers, not one, agree that actuation is
  always a human call.
- **Missing Companies and Business Registration Act registration is an
  unoverridable HARD hold** when the engagement declares it is required
  (`business-registration-missing`, the TUV analog of the AGO reference
  implementation's flagship `ao-entity-missing` check).
- **A claimed engagement fee that does not equal the independently
  recomputed `base-fee + monthly-rate x monitoring-months` is an
  unoverridable HARD hold** (`engagement-fee-mismatch`).
- **This is a SIX-check governor, not the AGO reference's seven.** The
  Tuvalu dossier does not ground a distinct tax-ID/corporate-number
  verification scheme separate from Companies and Business Registration
  Act registration, so there is no analog of AGO's `nif-unverified` check
  here. Padding the check count to match a sibling actor would itself be
  a fabrication -- honesty about a thinner dossier is a feature of this
  actor's design, not a gap to paper over.
- **Double-actuation is structurally prevented**: `:drafted?`/
  `:submitted?` dedicated facts (never a `:status` value) make drafting
  or submitting the same engagement twice an unoverridable HARD hold.
- **Every governor decision -- commit OR hold -- is written to an
  append-only audit ledger.** Nothing is silently dropped.
