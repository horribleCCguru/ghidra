/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package generic.theme.laf;

import static generic.theme.SystemThemeIds.*;

import javax.swing.UIDefaults;

public class MetalUiDefaultsMapper extends UiDefaultsMapper {

	protected MetalUiDefaultsMapper(UIDefaults defaults) {
		super(defaults);
	}

	@Override
	protected void registerIgnoredLafIds() {
		super.registerIgnoredLafIds();
		ignoredLafIds.add("textInactiveText");
	}

	@Override
	protected void assignSystemColorValues() {
		super.assignSystemColorValues();
		assignSystemColorFromLafId(FG_DISABLED_ID, "Label.disabledForeground");
		assignSystemColorFromLafId(FG_VIEW_ID, "windowText");
	}

}
