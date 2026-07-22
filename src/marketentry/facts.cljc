(ns marketentry.facts
  "Tuvalu (TUV) market-entry catalog.

  Every fact here traces to the verified research dossier gathered
  2026-07-22 against `finance.gov.tv` and `tuvalu-legislation.tv`
  (official Government of Tuvalu domains) plus a WIPO Lex mirror. This
  catalog is DELIBERATELY thinner and more conservative than several
  sibling jurisdictions in this actor family (compare
  `cloud-itonami-iso3166-ago`'s `AGO` entry, or `cloud-itonami-iso3166-
  fji`'s `FJI` entry): Tuvalu is a very small nation with limited
  digitized government information, and this iteration found NO
  confirmed national e-procurement portal and NO independently-
  verifiable distinct tax-ID/corporate-number scheme separate from
  ordinary business registration -- `:national-spec` says so honestly
  rather than inventing either, and there is accordingly no `:rep-*`/
  `:corporate-number-*` sub-map on the `TUV` entry (the same honest
  scope-narrowing discipline `cloud-itonami-iso3166-stp`'s `STP` entry,
  `cloud-itonami-iso3166-sdn`'s `SDN` entry and `cloud-itonami-iso3166-
  gnb`'s `GNB` entry already established for this family).

  What IS verified:

  - **Public procurement** is administered by a **Central Procurement
    Unit**, established within the ministry responsible for finance
    (the Ministry of Finance and Economic Development). The ministry's
    own official site (`finance.gov.tv`) states its purposes: developing
    public procurement policy, carrying out major procurement, and other
    functions as prescribed by regulation.
  - **Legal basis**: the Public Procurement Act, CAP 4.21 (2022 Revised
    Edition; the original enactment appears to be 2014). Official text
    hosted at `tuvalu-legislation.tv`.
  - **Business/commercial registration**: the Ministry of Finance
    administers the Companies Act, the Partnership Act, and the
    Companies and Business Registration Act(s), including Foreign Direct
    Investment Acts. The specific registration statute is the Companies
    and Business Registration Act, Chapter 40.12 (2008 Revised Edition) --
    official text hosted at `finance.gov.tv`, mirrored at WIPO Lex and
    the Tuvalu Trade Portal.
  - **Registration process**: businesses register by submitting a
    completed \"Business, Revenue & Customs Registration\" form to
    `business@gov.tv`; once business registration is approved, the
    operator can then apply for an Operational License. This is this
    vertical's FLAGSHIP check (see `marketentry.governor` /
    `marketentry.registry`): a single business-registration-missing
    check grounded in the Companies and Business Registration Act /
    Operational License process (`:requires-tuv-registration?`/
    `:has-tuv-registration?`).

  What is NOT verified, and is therefore NOT claimed:

  - No national e-procurement portal was confirmed for Tuvalu --
    `:national-spec` says so explicitly rather than inventing one.
  - No specific tax-ID scheme name/acronym was independently confirmed
    for Tuvalu in this research pass. Where tax/revenue registration is
    referenced below, it is grounded ONLY in the general \"Business,
    Revenue & Customs Registration form\" process described above, not a
    distinct named tax authority acronym -- accordingly there is no
    `corporate-number-spec-basis` sub-map here (see the honesty note in
    `marketentry.governor`: this is a SIX-check governor, not a padded
    seven, for exactly this reason).
  - No resident-representative/local-agent regime distinct from ordinary
    business registration was independently confirmed -- `rep-spec-
    basis` is honestly nil for TUV.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. TUV
  deliberately carries NO `:rep-owner-authority` and NO
  `:corporate-number-owner-authority` -- see the namespace docstring's
  honest-scope-narrowing note."
  {"TUV" {:name "Tuvalu"
          :owner-authority "the Central Procurement Unit, established within the ministry responsible for finance (the Ministry of Finance and Economic Development), per the ministry's own official site (finance.gov.tv): purposes are developing public procurement policy, carrying out major procurement, and other functions as prescribed by regulation"
          :legal-basis "the Public Procurement Act, CAP 4.21 (2022 Revised Edition; original enactment appears to be 2014)"
          :national-spec "Public Procurement Act CAP 4.21 procedures administered by the Central Procurement Unit within the Ministry of Finance and Economic Development. No national e-procurement self-service portal was confirmed for Tuvalu in this research pass -- not invented."
          :provenance "https://finance.gov.tv/ ; https://tuvalu-legislation.tv/cms/images/LEGISLATION/PRINCIPAL/2014/2014-0001/2014-0001_2.pdf"
          :required-evidence ["Companies and Business Registration Act (CAP 40.12, 2008 Revised Edition) business/company registration record"
                               "Business, Revenue & Customs Registration form submission record (submitted to business@gov.tv, per the Ministry of Finance's own registration process)"
                               "Operational License record (applied for once business registration is approved, per the Ministry of Finance's own registration process)"]
          :tuv-registration-owner-authority "Ministry of Finance (administers the Companies Act, the Partnership Act, and the Companies and Business Registration Act(s), including Foreign Direct Investment Acts)"
          :tuv-registration-legal-basis "Companies and Business Registration Act, CAP 40.12 (Chapter 40.12, 2008 Revised Edition)"
          :tuv-registration-provenance "https://finance.gov.tv/wp-content/uploads/2022/05/Companies-and-Business-Registration-Act.pdf ; https://www.wipo.int/wipolex/en/legislation/details/17704 ; https://tuvalu.tradeportal.org/media/CompaniesandBusinessRegistrationAct_1.pdf"}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tuv R0: " (count catalog)
                 " jurisdiction(s) seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "deliberately thin given Tuvalu's limited digitized "
                 "government information -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For TUV this is deliberately nil --
  no resident-representative/local-agent regime distinct from ordinary
  business registration was independently confirmed."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil. For TUV
  this is deliberately nil -- no specific tax-ID scheme name/acronym was
  independently confirmed separate from ordinary business registration."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn tuv-registration-spec-basis
  "The jurisdiction's business/company-registration regime, or nil. For
  TUV this is real and current -- the flagship check this vertical adds
  is grounded here (Companies and Business Registration Act, CAP
  40.12)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:tuv-registration-owner-authority sb)
      (select-keys sb [:tuv-registration-owner-authority
                       :tuv-registration-legal-basis
                       :tuv-registration-provenance]))))
