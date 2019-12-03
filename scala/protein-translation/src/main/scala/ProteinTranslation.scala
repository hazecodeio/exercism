object ProteinTranslation {

  val aminos = Map(
    Set("AUG") -> "Methionine",
    Set("UUU", "UUC") -> "Phenylalanine",
    Set("UUA", "UUG") -> "Leucine",
    Set("UCU", "UCC", "UCA", "UCG") -> "Serine",
    Set("UAU", "UAC") -> "Tyrosine",
    Set("UGU", "UGC") -> "Cysteine",
    Set("UGG") -> "Tryptophan")

  val stopCodons = Set("UAA", "UAG", "UGA")

  /**
   * alternative to:
   *  sliding(3, 3) -> grouped(3)
   *
   * @param rna
   * @return
   */
  def proteins(rna: String) = rna.toSeq.sliding(3, 3).map(_.mkString)
    .takeWhile(!stopCodons.contains(_))
    .flatMap(
      codon => aminos.keys
        .filter(_.contains(codon))
        .map(aminos(_))
    ).toSeq

}