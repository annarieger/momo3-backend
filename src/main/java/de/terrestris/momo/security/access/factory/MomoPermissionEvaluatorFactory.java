/**
 *
 */
package de.terrestris.momo.security.access.factory;

import de.terrestris.momo.model.MomoApplication;
import de.terrestris.momo.model.MomoLayer;
import de.terrestris.momo.model.MomoUser;
import de.terrestris.momo.model.MomoUserGroup;
import de.terrestris.momo.model.security.UserGroupRole;
import de.terrestris.momo.model.tree.DocumentTreeFolder;
import de.terrestris.momo.model.tree.DocumentTreeLeaf;
import de.terrestris.momo.model.tree.LayerTreeFolder;
import de.terrestris.momo.model.tree.LayerTreeLeaf;
import de.terrestris.momo.security.access.entity.DocumentTreeFolderPermissionEvaluator;
import de.terrestris.momo.security.access.entity.DocumentTreeLeafPermissionEvaluator;
import de.terrestris.momo.security.access.entity.ExtentPermissionEvaluator;
import de.terrestris.momo.security.access.entity.FilePermissionEvaluator;
import de.terrestris.momo.security.access.entity.LayerAppearancePermissionEvaluator;
import de.terrestris.momo.security.access.entity.LayerTreeFolderPermissionEvaluator;
import de.terrestris.momo.security.access.entity.LayerTreeLeafPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MapConfigPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MapPermissionEvaluator;
import de.terrestris.momo.security.access.entity.ModulePermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoAlwaysAllowReadPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoApplicationPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoLayerPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoPersistentObjectPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoUserGroupPermissionEvaluator;
import de.terrestris.momo.security.access.entity.MomoUserPermissionEvaluator;
import de.terrestris.momo.security.access.entity.TreeNodePermissionEvaluator;
import de.terrestris.momo.security.access.entity.UserGroupRolePermissionEvaluator;
import de.terrestris.shogun2.model.File;
import de.terrestris.shogun2.model.PersistentObject;
import de.terrestris.shogun2.model.Role;
import de.terrestris.shogun2.model.interceptor.InterceptorRule;
import de.terrestris.shogun2.model.layer.appearance.LayerAppearance;
import de.terrestris.shogun2.model.layer.source.LayerDataSource;
import de.terrestris.shogun2.model.layer.util.Extent;
import de.terrestris.shogun2.model.layer.util.TileGrid;
import de.terrestris.shogun2.model.layout.Layout;
import de.terrestris.shogun2.model.map.MapConfig;
import de.terrestris.shogun2.model.map.MapControl;
import de.terrestris.shogun2.model.module.Map;
import de.terrestris.shogun2.model.module.Module;
import de.terrestris.shogun2.model.security.PermissionCollection;
import de.terrestris.shogun2.model.token.Token;
import de.terrestris.shogun2.model.tree.TreeNode;
import de.terrestris.shogun2.security.access.entity.PermissionCollectionPermissionEvaluator;
import de.terrestris.shogun2.security.access.entity.PersistentObjectPermissionEvaluator;
import de.terrestris.shogun2.security.access.factory.EntityPermissionEvaluatorFactory;

/**
 *
 * This class has to be configured to be used as the permissionEvaluator (of
 * SHOGun2) in the security XML of this project.
 *
 * @author Nils Bühner
 *
 */
public class MomoPermissionEvaluatorFactory<E extends PersistentObject> extends EntityPermissionEvaluatorFactory<E> {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PersistentObjectPermissionEvaluator<E> getEntityPermissionEvaluator(
			final Class<E> entityClass) {

		if(DocumentTreeFolder.class.isAssignableFrom(entityClass)) {
			return new DocumentTreeFolderPermissionEvaluator();
		}
		if(DocumentTreeLeaf.class.isAssignableFrom(entityClass)) {
			return new DocumentTreeLeafPermissionEvaluator();
		}
		if(MomoLayer.class.isAssignableFrom(entityClass)) {
			return new MomoLayerPermissionEvaluator();
		}
		if(LayerAppearance.class.isAssignableFrom(entityClass)) {
			return new LayerAppearancePermissionEvaluator();
		}
		if(MomoApplication.class.isAssignableFrom(entityClass)) {
			return new MomoApplicationPermissionEvaluator();
		}
		if(Extent.class.isAssignableFrom(entityClass)) {
			return new ExtentPermissionEvaluator();
		}
		if(MapConfig.class.isAssignableFrom(entityClass)) {
			return new MapConfigPermissionEvaluator();
		}
		if(Map.class.isAssignableFrom(entityClass)) {
			return new MapPermissionEvaluator();
		}
		if(Module.class.isAssignableFrom(entityClass)) {
			return new ModulePermissionEvaluator();
		}
		if (LayerTreeLeaf.class.isAssignableFrom(entityClass)) {
			return new LayerTreeLeafPermissionEvaluator();
		}
		if (LayerTreeFolder.class.isAssignableFrom(entityClass)) {
			return new LayerTreeFolderPermissionEvaluator();
		}
		if(TreeNode.class.isAssignableFrom(entityClass)) {
			return new TreeNodePermissionEvaluator();
		}
		if(MomoUserGroup.class.isAssignableFrom(entityClass)) {
			return new MomoUserGroupPermissionEvaluator();
		}
		if(MomoUser.class.isAssignableFrom(entityClass)) {
			return new MomoUserPermissionEvaluator();
		}
		if(UserGroupRole.class.isAssignableFrom(entityClass)) {
			return new UserGroupRolePermissionEvaluator();
		}
		if(PermissionCollection.class.isAssignableFrom(entityClass)) {
			return new PermissionCollectionPermissionEvaluator();
		}
		if(File.class.isAssignableFrom(entityClass)) {
			return new FilePermissionEvaluator();
		}

		// The following types (and subclasses) may be READ by everyone
		// by default. If a type is not listed here, explicit Permissions
		// have to be set for the entities of these types.
		//
		// NOT listed here (and therefore "fully secured") are the following
		// classes AND (!) their subclasses:
		//
		// * Layer
		// * Application
		// * File
		// * Person
		// * UserGroup
		if(InterceptorRule.class.isAssignableFrom(entityClass) ||
			LayerAppearance.class.isAssignableFrom(entityClass) ||
			LayerDataSource.class.isAssignableFrom(entityClass) ||
			Layout.class.isAssignableFrom(entityClass) ||
			MapControl.class.isAssignableFrom(entityClass) ||
			Role.class.isAssignableFrom(entityClass) ||
			TileGrid.class.isAssignableFrom(entityClass) ||
			Token.class.isAssignableFrom(entityClass)) {

			// always grants READ permission (but no other permission)
			// project specific requirements require implementations
			// of custom permission evaluators
			return new MomoAlwaysAllowReadPermissionEvaluator();
		}

		// Call default momo PermissionEvaluator otherwise.
		return new MomoPersistentObjectPermissionEvaluator<E>(entityClass);

	}

}
