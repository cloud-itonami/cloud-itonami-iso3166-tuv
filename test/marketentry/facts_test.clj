(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest tuv-has-spec-basis
  (let [sb (facts/spec-basis "TUV")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (= 3 (count (:required-evidence sb)))
        "thinner dossier than AGO's 4 required-evidence items -- honest, not padded")))

(deftest tuv-has-no-rep-or-corporate-number-sub-map
  (testing "the dossier does not ground a distinct resident-rep regime or tax-ID scheme -- unlike AGO/FJI"
    (is (nil? (facts/rep-spec-basis "TUV")))
    (is (nil? (facts/corporate-number-spec-basis "TUV")))))

(deftest tuv-registration-spec-basis-is-grounded
  (testing "the flagship check's spec-basis names the Companies and Business Registration Act, not an invented tax-ID scheme"
    (let [sb (facts/tuv-registration-spec-basis "TUV")]
      (is (some? sb))
      (is (re-find #"Companies and Business Registration Act" (:tuv-registration-legal-basis sb)))
      (is (re-find #"CAP 40.12" (:tuv-registration-legal-basis sb))))))

(deftest tuv-national-spec-does-not-claim-an-e-procurement-portal
  (testing "no national e-procurement portal was confirmed for Tuvalu -- must not be invented"
    (let [sb (facts/spec-basis "TUV")]
      (is (re-find #"[Nn]o national e-procurement" (:national-spec sb))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "TUV")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "TUV" all)))
    (is (not (facts/required-evidence-satisfied? "TUV" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["TUV" "ATL"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
