package dagger.util;

import com.sun.tools.javac.code.Symbol;
import dagger.Module;

@Module
abstract class FragmentBuildersModule {
  abstract Symbol.ClassSymbol contributesFragment();
}
