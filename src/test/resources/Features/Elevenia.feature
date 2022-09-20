Feature: Test alami
  Scenario: Scenarios to test elevenia
    Given Open web URL "https://www.elevenia.co.id"
    And Search "Komputer"
    And Select "Produk terlaris"
    And Select first product
    And Fill with quantity "3"
    And Add to cart
    And Verify pop up, if there is any pop up confirm yes "Ya", if not add to cart
    And Select change courier
    When Select batal on the pop up
    And Select "Hapus" and "OK"
    Then Verify product not exist again on cart