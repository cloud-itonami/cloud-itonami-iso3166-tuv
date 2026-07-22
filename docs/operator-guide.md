# Operator guide — TUV

Human-gated filing only.

## What runs automatically

- `:engagement/intake` may auto-commit at phase 3 when the governor is
  clean (no portal-facing risk).

## What always needs your sign-off

- `:jurisdiction/assess` -- always requires human approval, even when
  clean.
- `:filing/draft` -- always requires human approval. This prepares an
  unsigned filing-draft record; it does not itself submit anything to a
  government registry.
- `:filing/submit` -- always requires human approval. This is the
  real-world act of submitting a filing; there is no phase, no
  confidence score, and no governor state that lets this auto-commit.

## What will always be refused, with no override

- A jurisdiction proposal that cannot cite an official source
  (`marketentry.facts`) -- `:no-spec-basis`.
- A `:filing/draft`/`:filing/submit` proposal before the jurisdiction has
  a full evidence checklist on file -- `:evidence-incomplete`.
- A `:filing/submit` for an engagement that declares it requires
  Companies and Business Registration Act (CAP 40.12) registration but
  has not confirmed it -- `:business-registration-missing`. This check is
  CONDITIONAL on the engagement's own `:requires-tuv-registration?`
  ground truth: not every engagement necessarily requires it.
- A `:filing/submit` whose claimed fee does not equal
  `base-fee + monthly-rate x monitoring-months` -- `:engagement-fee-mismatch`.
- Drafting or submitting the same engagement a second time --
  `:already-drafted` / `:already-submitted`.

## What this actor does NOT claim

There is no national e-procurement portal on file for Tuvalu -- the
operative legal basis is the **Public Procurement Act, CAP 4.21 (2022
Revised Edition)**, administered by the Central Procurement Unit within
the Ministry of Finance and Economic Development. There is no tax-ID/
corporate-number scheme on file distinct from ordinary Companies and
Business Registration Act (CAP 40.12) registration, and no resident-
representative/local-agent regime distinct from ordinary business
registration. This reflects genuinely limited digitized government
information for Tuvalu, a very small nation -- it is a scope limit, not
an omission. If you find a verifiable source for any of these, extend
`src/marketentry/facts.cljc`'s `catalog` -- do not hand-edit a claim into
this guide or any other doc without an official source.
