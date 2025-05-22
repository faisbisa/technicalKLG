# technicalKLG
# For Test Case :
https://docs.google.com/spreadsheets/d/1DPyqGyK8nfnTsefM134wBi1QZwalcEOYbRVQHmTHk0A/edit?gid=0#gid=0

**Langkah-langkah upload to Git jika token sudah expired :**
Masuk ke GitHub → https://github.com/settings/tokens

Klik “Generate new token (classic)” → isi:

Note: push access from Mac

Expiration: pilih sesuai keinginan (30 days/90 days)

Scopes: centang repo

Klik Generate token, lalu salin token tersebut (jangan sampai hilang).

Sekarang, saat Git minta username dan password:

Username: tetap pakai username GitHub kamu (misal: faisbisa)

Password: tempel token yang tadi kamu salin

Untuk menyimpan supaya tidak terus diminta:

Jalankan:

bash
Copy
Edit
git config --global credential.helper osxkeychain
