# AutomationSeleniumOYO

1. User membuka halaman web
2. User memilih menu makanan yang diinginkan. untuk test ini memilih semuanya
3. User bisa memberikan notes untuk setiap makanan
4. User dapat memilih jumlah produk yang akan dibeli
5. Menekan Add To Cart.
6. User dapat memilihat produk yang ada di Cart
7. user diperlukan untuk memasukan Nama dan Nomor HP untuk melaukan proses Checkout
8. user dapat memilih tipe delivery (x)
9. user memasukan email dan detail room.
10. user dapat memilih metode pembayaran, untuk parallel 1 memilih GoPay dan parallel 2 memilih OVO
11. user dapat melanjukan pembayaran dengan klik Continue to Pay, namun untuk OVO memasukan no HP sebelum klik to Continue To Pay

Notes: terdapat 4 Parallel dengan value :
- no.| Pembayaran  |  Order By
- 1  |    OVO      |  Delivery
- 2  |    OVO      |  Pickup
- 3  |   GoPay     |  Delivery
- 4  |   GoPay     |  Pickup
