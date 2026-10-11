#!/usr/bin/env bash
# Compila la app de muestra a un APK sin Gradle ni Android SDK completo.
# Requiere: aapt, dalvik-exchange (dx), apksigner, zipalign, keytool, javac
# y un android.jar (por defecto el de android-sdk-platform-23 de Ubuntu).
set -euo pipefail

cd "$(dirname "$0")"
ANDROID_JAR="${ANDROID_JAR:-/usr/lib/android-sdk/platforms/android-23/android.jar}"
SRC=app/src/main
BUILD=build
OUT=dist/NuevoProyecto-muestra.apk

rm -rf "$BUILD" && mkdir -p "$BUILD/classes" "$BUILD/gen" dist

aapt package -f -m -J "$BUILD/gen" -M "$SRC/AndroidManifest.xml" -S "$SRC/res" -I "$ANDROID_JAR" \
    -F "$BUILD/unsigned.apk"

javac --release 8 -Xlint:-options -cp "$ANDROID_JAR" -d "$BUILD/classes" \
    $(find "$SRC/java" "$BUILD/gen" -name '*.java')

dalvik-exchange --dex --output="$BUILD/classes.dex" "$BUILD/classes"
(cd "$BUILD" && aapt add unsigned.apk classes.dex >/dev/null)

zipalign -f 4 "$BUILD/unsigned.apk" "$BUILD/aligned.apk"

KEYSTORE="$BUILD/debug.keystore"
keytool -genkeypair -keystore "$KEYSTORE" -storepass android -keypass android \
    -alias debug -keyalg RSA -keysize 2048 -validity 10000 \
    -dname "CN=Android Debug,O=Android,C=US" 2>/dev/null

apksigner sign --ks "$KEYSTORE" --ks-pass pass:android --key-pass pass:android \
    --out "$OUT" "$BUILD/aligned.apk"
apksigner verify "$OUT"
echo "APK listo: $OUT"
